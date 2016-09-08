springBatch-reader-writer-dinesh
--------------------------
Ref: http://www.dineshonjava.com/2014/02/spring-batch-itemreader-itemwriter.html#.V9FNKNJ97IU

In this tutorial we will discuss about the three most important interfaces of spring batch and an overview of Spring Batch item reader and writer with a sample application. One of the important goals of a batch processing framework is to read large amounts of data, perform some business processing/transformation and write out the result. Spring Batch Framework supports this bulk reading, processing and writing using three key interfaces: ItemReader, ItemProcessor and ItemWriter.

1. ItemReader is the means for providing data from many different types of input. ItemReader interface is the means for reading bulk data in a bulk processing system. There are many different implementations of ItemReader interface. All implementations are expected to be stateful and will be called multiple times for each batch, with each call to read() returning a different value and finally returning null when all input data is exhausted. Below are few frequently used implementations of ItemReader.


ItemReader Implementation	Description
FlatFileItemReader	Reads lines of data from input file. Typically read line describe records with fields of data defined by fixed positions in the file or delimited by some special character (e.g. Comma (,), Pipe (|) etc).
JdbcCursorItemReader	Opens a JDBC cursor and continually retrieves the next row in the ResultSet.
StoredProcedureItemReader	Executes a stored procedure and then reads the returned cursor and continually retrieves the next row in the ResultSet.
All the above implementations override the read() method from the ItemReader interface. The read method defines the most essential contract of the ItemReader. It returns one item or null if no more items are left. An item might represent a line in a file, a row in a database and so on.

2. ItemWriter is similar in functionality to an ItemReader, but with inverse operations. ItemWriter is a interface for generic output operations. Implementation class will be responsible for serializing objects as necessary. Resources still need to be located, opened and closed but they differ in that an ItemWriter writes out, rather than reading in. For databases these may be inserts or updates.

The write method defines the most essential contract of the ItemWriter. It will attempt to write out the list of items passed in as long as it is open. As it is expected that items will be ‘batched’ together into a chunk and then output, the interface accepts a list of items, rather than an item by itself. Once the items are written out , any flushing that may be necessary can be performed before returning from the write method. 


ItemWriter Implementation	Description
FlatFileItemWriter	Writes data to a file or stream. Uses buffered writer to improve performance.
StaxEventItemWriter	An implementation of ItemWriter which uses StAX and Marshaller for serializing object to XML.

3. ItemProcessor- The ItemReader and ItemWriter interfaces are both very useful for their specific tasks, but what if you want to insert business logic before writing? An ItemProcessor is very simple interface for item transformation. Given one object, transform it and return another. Any business/transformation logic can be plugged into this component. Assume an ItemReader provides a class of type User, and it needs to be converted to type Employee before being written out. An ItemProcessor can be written that performs the conversion. Another typical use for an item processor is to filter out records before they are passed to the ItemWriter. Filtering simply indicates that a record should not be written.