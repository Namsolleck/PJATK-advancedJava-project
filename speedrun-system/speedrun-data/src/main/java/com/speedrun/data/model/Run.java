@Entity
@Getter @Setter
public class Run{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double seconds;
    private String videoLink;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="runner_id")
    private Runner runner;

    @ManyToOne
    @JoinColumn(name="platform_id")
    private Platform platform;
}
