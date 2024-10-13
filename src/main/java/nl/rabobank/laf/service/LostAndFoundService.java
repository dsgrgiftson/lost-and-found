package nl.rabobank.laf.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import nl.rabobank.laf.entity.LostItem;
import nl.rabobank.laf.entity.LostItemClaim;
import nl.rabobank.laf.entity.Users;
import nl.rabobank.laf.exception.LostAndFoundException;
import nl.rabobank.laf.model.ClaimItemsRequestDto;
import nl.rabobank.laf.model.ClaimItemsResponseDto;
import nl.rabobank.laf.model.ClaimedItemsResponseDto;
import nl.rabobank.laf.model.LostItemDto;
import nl.rabobank.laf.repository.LostItemClaimRepository;
import nl.rabobank.laf.repository.LostItemRepository;
import nl.rabobank.laf.repository.UsersRepository;
import nl.rabobank.laf.util.FileUtils;
import nl.rabobank.laf.util.TransformationUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service class for handling operations related to lost items.
 */
@Component
@AllArgsConstructor
public class LostAndFoundService {

    private final FileUtils fileUtils;
    private final PdfFileService pdfFileService;
    private final TransformationUtil transformationUtil;
    private final LostItemRepository lostItemRepository;
    private final LostItemClaimRepository lostItemClaimRepository;
    private final UsersRepository usersRepository;

    /**
     * Stores lost data from the provided PDF file.
     *
     * @param lostFile the PDF file containing lost data
     * @return a list of LostItemDto objects representing the stored lost items
     * @throws LostAndFoundException if an error occurs while processing the file
     */
    public List<LostItemDto> storeLostData(MultipartFile lostFile) {
        try {
            return transformationUtil.lostItemEntityToDto(
                    lostItemRepository.saveAll(
                            transformationUtil.lostItemDtoToEntity(
                                    pdfFileService.getLostItems(
                                            fileUtils.moveToUploadDir(lostFile)))));
        } catch (IOException e) {
            throw new LostAndFoundException("Exception occurred while trying to get lost items from PDF file and saving it", e);
        } finally {
            fileUtils.deleteFile(lostFile);
        }
    }

    /**
     * Reads all lost data from the repository.
     *
     * @return a list of LostItemDto objects representing the lost items
     */
    public List<LostItemDto> readLostData() {
        return transformationUtil.lostItemEntityToDto(lostItemRepository.findAll());
    }

    /**
     * Claims lost items based on the provided request.
     *
     * @param claimItemsRequestDto the request containing the items to be claimed
     * @return a list of ClaimItemsResponseDto objects representing the claim results
     * @throws LostAndFoundException if the user or any of the lost items are not found, or if the claimed quantity exceeds available quantity
     */
    public List<ClaimItemsResponseDto> claimLostItem(ClaimItemsRequestDto claimItemsRequestDto) {
        Optional<Users> user = usersRepository.findById(claimItemsRequestDto.claimantUserId());
        if (user.isEmpty()) {
            throw new LostAndFoundException("User %s not found. So claim is invalid".formatted(claimItemsRequestDto.claimantUserId()));
        }

        List<ClaimItemsResponseDto> claimItemsResponseDtos = new ArrayList<ClaimItemsResponseDto>();
        claimItemsRequestDto.itemsAndQuantity().forEach(itemAndQuantity -> {
            Optional<LostItem> lostItem = lostItemRepository.findById(itemAndQuantity.lostItemId());
            if (lostItem.isEmpty()) {
                throw new LostAndFoundException("Lost item %s not found".formatted(itemAndQuantity.lostItemId()));
            } else if (lostItem.get().getQuantity() < itemAndQuantity.claimedQuantity()) {
                claimItemsResponseDtos.add(new ClaimItemsResponseDto(lostItem.get().getLostItemId(), itemAndQuantity.claimedQuantity(), false, null));
            } else {
                LostItemClaim lostItemClaim = new LostItemClaim();
                lostItemClaim.setLostItem(lostItem.get());
                lostItemClaim.setUser(user.get());
                lostItemClaim.setClaimedQuantity(itemAndQuantity.claimedQuantity());
                LostItemClaim savedClaim = lostItemClaimRepository.save(lostItemClaim);
                claimItemsResponseDtos.add(new ClaimItemsResponseDto(lostItem.get().getLostItemId(), itemAndQuantity.claimedQuantity(), true, savedClaim.getClaimTimestamp()));
            }
        });
        return claimItemsResponseDtos;
    }

    /**
     * Retrieves all claimed items from the repository.
     *
     * @return a list of ClaimedItemsResponseDto objects representing the claimed items
     */
    public List<ClaimedItemsResponseDto> retrieveClaimedItems() {
        return lostItemClaimRepository.findAll()
                .stream()
                .map(lostItemClaim -> new ClaimedItemsResponseDto(
                        lostItemClaim.getUser().getUserId(),
                        lostItemClaim.getUser().getFirstName(),
                        lostItemClaim.getLostItem().getLostItemId(),
                        lostItemClaim.getLostItem().getItemName(),
                        lostItemClaim.getClaimedQuantity(),
                        lostItemClaim.getClaimTimestamp()))
                .toList();
    }
}