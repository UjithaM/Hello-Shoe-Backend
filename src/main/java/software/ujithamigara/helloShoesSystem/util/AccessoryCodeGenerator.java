package software.ujithamigara.helloShoesSystem.util;

import org.springframework.stereotype.Component;
import software.ujithamigara.helloShoesSystem.entity.enums.AccessoriesVerities;

@Component
public class AccessoryCodeGenerator {
    public static String generateAccessoryCode(AccessoriesVerities accessoryType, long itemNumber) {
        String accessoryCode = getAccessoryTypeCode(accessoryType);
        String itemNumberCode = String.format("%05d", itemNumber +1); // Ensure itemNumber is always 5 digits

        return accessoryCode + itemNumberCode;
    }
    private static String getAccessoryTypeCode(AccessoriesVerities accessoryType) {
        switch (accessoryType) {
            case SHOE_SHAMPOO: return "SHMP";
            case POLISH_BLACK: return "POLB";
            case POLISH_BROWN: return "POLBR";
            case POLISH_DARK_BROWN: return "POLDBR";
            case FULL_SOCKS: return "SOF";
            case HALF_SOCKS: return "SOH";
            default: return "";
        }
    }
}
