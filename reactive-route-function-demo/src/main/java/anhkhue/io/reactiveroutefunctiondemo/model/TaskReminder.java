package anhkhue.io.reactiveroutefunctiondemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class TaskReminder {

    @Id
    private String id;
    @NonNull
    private String task;
    @NonNull
    private long remindDuration;
}
