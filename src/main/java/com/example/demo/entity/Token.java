@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String tokenNumber;

    private String status; // WAITING, SERVING, COMPLETED, CANCELLED

    @ManyToOne
    private ServiceCounter serviceCounter;

    // getters & setters
}
