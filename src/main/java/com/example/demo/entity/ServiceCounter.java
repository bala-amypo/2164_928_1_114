@Entity
@Table(name = "service_counter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String counterName;

    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;
}
