package softuni.exam.models.dto.volcano;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VolcanoExportDTO {

    private String name;
    private Integer elevation;
    private String country;
    // Leave this as reference:
   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
   // @DateTimeFormat(pattern = "yyyy-MM-dd")
  //   @XmlJavaTypeAdapter(DateAdapter.class)
    private Date lastEruption;

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // or any format you need
        String formattedDate = (lastEruption != null) ? dateFormat.format(lastEruption) : "N/A";

        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = (lastEruption != null) ? lastEruption.format(formatter) : "N/A";*/

        return  "Volcano: " + name + "\n" +
                "   *Located in: " + country + "\n" +
                "   **Elevation: " + elevation + "\n" +
                "   ***Last eruption on: " + formattedDate + "\n";
    }
}
