package nl.rabobank.laf.util;

import java.util.List;
import java.util.stream.Collectors;
import nl.rabobank.laf.entity.LostItem;
import nl.rabobank.laf.model.LostItemDto;
import org.springframework.stereotype.Component;

/**
 * Utility class for transforming between LostItem entities and LostItemDto objects.
 * This class provides methods to convert lists of LostItemDto objects to LostItem entities and vice versa.
 */
@Component
public class TransformationUtil {

    /**
     * Converts a list of LostItemDto objects to a list of LostItem entities.
     *
     * @param lostItemDtos the list of LostItemDto objects to be converted
     * @return a list of LostItem entities
     */
    public List<LostItem> lostItemDtoToEntity(List<LostItemDto> lostItemDtos) {
        return lostItemDtos.parallelStream()
                .map(lostItemDto -> new LostItem(lostItemDto.lostItemId(), lostItemDto.itemName(), lostItemDto.quantity(), lostItemDto.place()))
                .collect(Collectors.toList());
    }

    /**
     * Converts a list of LostItem entities to a list of LostItemDto objects.
     *
     * @param lostItems the list of LostItem entities to be converted
     * @return a list of LostItemDto objects
     */
    public List<LostItemDto> lostItemEntityToDto(List<LostItem> lostItems) {
        return lostItems.parallelStream()
                .map(lostItem -> new LostItemDto(lostItem.getLostItemId(), lostItem.getItemName(), lostItem.getQuantity(), lostItem.getPlace()))
                .collect(Collectors.toList());
    }

}