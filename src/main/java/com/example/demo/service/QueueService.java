package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class QueueService {

    private final Queue<String> queue = new LinkedList<>();

    // Add item to queue
    public void enqueue(String message) {
        queue.offer(message);
    }

    // Remove item from queue
    public String dequeue() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.poll();
    }

    // Peek item
    public String peek() {
        return queue.peek();
    }

    // Get queue size
    public int size() {
        return queue.size();
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
