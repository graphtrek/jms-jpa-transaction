package co.grtk.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParallelService {

    private ThreadPoolTaskExecutor taskExecutor;

    public ParallelService(ThreadPoolTaskExecutor taskExecutor) {
        log.info("TaskExecutor initialized core-pool-soze:{}", taskExecutor.getCorePoolSize());
        this.taskExecutor = taskExecutor;
    }
}
