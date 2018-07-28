databaseChangeLog {
  changeSet(id:'core_atg_proc_das_dms_queue_flag',author:'hallatech',context:'ddl') {
    createProcedure """
      create or replace procedure dms_queue_flag
      (Pbatch_size    IN NUMBER
      ,Pread_lock     IN NUMBER
      ,Pdelivery_date IN NUMBER
      ,Pclient_id     IN NUMBER
      ,Pqueue_id      IN NUMBER
      ,Pupdate_count  OUT NUMBER)
      as
                   Begin
          UPDATE dms_queue_entry qe
          SET qe.handling_client_id = Pclient_id, 
              qe.read_state = Pread_lock 
          WHERE ROWNUM < Pbatch_size
            AND qe.handling_client_id < 0 
            AND qe.delivery_date < Pdelivery_date 
            AND qe.queue_id = Pqueue_id;
          Pupdate_count := SQL%ROWCOUNT;
        END;
    """
  }
  changeSet(id:'core_atg_proc_das_dms_topic_flag',author:'hallatech',context:'ddl') {
    createProcedure """
      create or replace procedure dms_topic_flag
      (Pbatch_size    IN NUMBER
      ,Pread_lock     IN NUMBER
      ,Pdelivery_date IN NUMBER
      ,Psubscriber_id IN NUMBER
      ,Pupdate_count  OUT NUMBER)
      as
                   Begin
          UPDATE dms_topic_entry te 
          SET te.read_state = Pread_lock 
          WHERE ROWNUM < Pbatch_size
            AND te.delivery_date < Pdelivery_date 
            AND te.read_state = 0 
            AND te.subscriber_id = Psubscriber_id;
          Pupdate_count := SQL%ROWCOUNT;
        END; 
    """
  }

}

