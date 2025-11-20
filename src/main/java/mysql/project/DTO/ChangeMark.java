package mysql.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeMark {
    Integer studentId;
    Integer studentMark;
    String subject;
}
