import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Article {
    private String title;
    private String url;
    private String author;
    private Long numComments;
    private Long storyId;
    private String storyTitle;
    private String storyUrl;
    private Long parentId;
    private Date createdAt;
    private String name;
}
