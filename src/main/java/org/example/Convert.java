package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Convert {
    public static ObjectMapper mapper = new ObjectMapper();

    public record Post(String id, String text, String type, String user, Integer upvotes) {

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Post) obj;
            return this.id == that.id &&
                    Objects.equals(this.text, that.text) &&
                    Objects.equals(this.type, that.type) &&
                    Objects.equals(this.user, that.user) &&
                    Objects.equals(this.upvotes, that.upvotes);
        }

        @Override
        public String toString() {
            return "Post[" +
                    "id=" + id + ", " +
                    "text=" + text + ", " +
                    "type=" + type + ", " +
                    "user=" + user + ", " +
                    "upvotes=" + upvotes + ']';
        }


    }

    public static List<Post> jsonToJava(CloseableHttpResponse response) throws IOException {

        List<Post> posts = mapper.readValue(
                response.getEntity().getContent(),
                new TypeReference<>() {
                }
        );
        return posts;
    }

}
