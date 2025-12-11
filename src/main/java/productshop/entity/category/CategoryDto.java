package productshop.entity.category;

public class CategoryDto {

    private Integer id;
    private String name;

    public CategoryDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
