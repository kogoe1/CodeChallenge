This Application uses Spring boot.
The processed log events are persisted using spring JPA

Parallel Streams is used for in-built multi-threaded processing of the log events

To Run the application run the below command from the application directory. `<full_path_to_log_file>` should be replaced with the full path of the log file to be processed. 
```bash
./gradlew bootRun --args=<full_path_to_log_file>
```