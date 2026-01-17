@Entity
@Getter @Setter
public class Category {
    @Id
    @GeneneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;
}