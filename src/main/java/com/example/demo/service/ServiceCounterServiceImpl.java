@Service
public class QueueServiceImpl {

    private final QueuePositionRepository repo;

    public QueueServiceImpl(QueuePositionRepository repo) {
        this.repo = repo;
    }

    public QueuePosition updateQueuePosition(Long id, Integer position) {
        QueuePosition qp = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        qp.setPosition(position);
        return repo.save(qp);
    }

    public Integer getPosition(Long id) {
        return repo.findById(id)
                .map(QueuePosition::getPosition)
                .orElse(0);
    }
}
