package procheacksa.students.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidatsImages {
    private String id;
    private String cleEtrangere;
    private String type;
    // image bytes can have large lengths so we specify a value
    // which is more than the default length for picByte column
    //picByte;
    // private Resource
    private byte[] picByte;


    //-------------

}
