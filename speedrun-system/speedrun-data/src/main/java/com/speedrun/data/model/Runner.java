@Entity
@Getter @Setter
public class Runner{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String country;
}