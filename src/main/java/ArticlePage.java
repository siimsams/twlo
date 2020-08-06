import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticlePage {
    private Long page;
    private Long perPage;
    private Long total;
    private Long totalPages;
    private List<Article> data;
}
