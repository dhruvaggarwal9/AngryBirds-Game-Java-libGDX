#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>
#include <time.h>
#include <signal.h>

typedef struct process {
    pid_t pid;                 
    time_t start_time;         
    time_t end_time;           
    time_t total_run_time;     
    time_t total_wait_time;    

    struct process *next;      
} process;

typedef struct readyq {
    process* first;
    process* last;
} readyq;

readyq* createQueue()
{
    
    readyq* thisq = (readyq*)malloc(sizeof(readyq));
    thisq->first = NULL;
    thisq->last = NULL;
    return thisq;
}


void createnewprocess(int pipe_fd, readyq *queue) {

    pid_t thispid;
    read(pipe_fd, &thispid, sizeof(pid_t));
    process* thisprocess = (process*)malloc(sizeof(process));
    

    thisprocess->pid = thispid;
    thisprocess->start_time = time(NULL);  
    thisprocess->end_time = 0;             
    thisprocess->total_run_time = 0;       
    thisprocess->total_wait_time = 0;      
    thisprocess->next = NULL;              

   
    if (queue->last == NULL) {
        
        queue->first = thisprocess;
        queue->last = thisprocess;
    } 
    else {
        queue->last->next = thisprocess;
        queue->last = thisprocess;
    }
}

void enqueue(readyq* q, process* new_process) {
   
    if (q->last == NULL) {
        q->first = new_process;
        q->last = new_process;
    }
    else{
    q->last->next = new_process;
    q->last = new_process;
    }
}

process* dequeue(readyq* q) {
    
    if (isEmpty(q)) {
        return NULL;
    }
    process* temp = q->first;
    q->first = q->first->next;
    
    if (q->first == NULL) {
        q->last = NULL;
    }
    return temp;
}

int isEmpty(readyq* q)
{
    return q->first == NULL;
}

void properties (int pipe_ncpu, int pipe_tslice, int *nCPU, int *tSlice){
    
    read(pipe_ncpu, nCPU, sizeof(int));
    read(pipe_tslice, tSlice, sizeof(int));
    
}
