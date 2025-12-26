package com.example.demo.service.impl;

import com.example.demo.entity.QueueEntry;
import com.example.demo.repository.QueueRepository;
import com.example.demo.service.QueueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueServiceImpl implements QueueService {

    private final QueueRepository queueRepository;

    public QueueServiceImpl(QueueRepository queueRepository) {
        this.queueRepository = queueRepository;
    }

    @Override
    public QueueEntry createQueue(QueueEntry queueEntry) {
        return queueRepository.save(queueEntry);
    }

    @Override
    public List<QueueEntry> getAllQueues() {
        return queueRepository.findAll();
    }

    @Override
    public QueueEntry getQueueById(Long id) {
        return queueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Queue not found with id: " + id));
    }

    @Override
    public QueueEntry updateQueue(Long id, QueueEntry queueEntry) {
        QueueEntry existingQueue = getQueueById(id);

        existingQueue.setName(queueEntry.getName());
        existingQueue.setStatus(queueEntry.getStatus());

        return queueRepository.save(existingQueue);
    }

    @Override
    public void deleteQueue(Long id) {
        QueueEntry existingQueue = getQueueById(id);
        queueRepository.delete(existingQueue);
    }
}
