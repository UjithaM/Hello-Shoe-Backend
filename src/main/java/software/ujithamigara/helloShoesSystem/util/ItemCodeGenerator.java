package software.ujithamigara.helloShoesSystem.util;

import org.springframework.stereotype.Component;
import software.ujithamigara.helloShoesSystem.entity.enums.Gender;
import software.ujithamigara.helloShoesSystem.entity.enums.Occasion;
import software.ujithamigara.helloShoesSystem.entity.enums.Verities;
@Component
public class ItemCodeGenerator {

    public static String generateItemCode(Gender gender, Occasion occasion, Verities verities,  int size ,long itemNumber) {
        String genderCode = gender == Gender.MALE ? "M" : "W";
        String occasionCode = getOccasionCode(occasion);
        String veritiesCode = getVeritiesCode(verities);
        String itemNumberCode = String.format("%05d", itemNumber);


        return genderCode + size + occasionCode + veritiesCode + itemNumberCode;
    }

    private static String getOccasionCode(Occasion occasion) {
        return switch (occasion) {
            case FORMAL -> "F";
            case CASUAL -> "C";
            case INDUSTRIAL -> "I";
            case SPORTS -> "S";
            default -> "";
        };
    }

    private static String getVeritiesCode(Verities verities) {
        return switch (verities) {
            case HEEL -> "H";
            case FLAT -> "F";
            case WEDGES -> "W";
            case FLIP_FLOP -> "FF";
            case SANDALS -> "SD";
            case SHOES -> "S";
            case SLIPPER -> "SL";
            default -> "";
        };
    }

}
