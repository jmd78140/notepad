-- Create the note table
CREATE TABLE note (
    id SERIAL PRIMARY KEY, 
    title VARCHAR(255) NOT NULL, 
    content VARCHAR(255) NOT NULL
);

-- Insert sample data into the note table
INSERT INTO note (title, content) VALUES ('What is Cassandra?', 'Cassandra is a NoSQL database that belongs to the Column Family NoSQL database category.');