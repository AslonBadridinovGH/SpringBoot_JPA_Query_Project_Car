package uz.pdp.playload;

import lombok.Data;
import java.util.List;

@Data
public class UserDto {

    private String  name;
    private String  houseNumber;
    private String  street;
    private Integer districtId;

    private List<Integer> carIds;


}
