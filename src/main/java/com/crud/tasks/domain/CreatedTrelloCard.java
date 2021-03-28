package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.attachment.AttachmentMarshaller;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloCard {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("shortUrl")
    private String shortUrl;

    /*
    @JsonProperty("badges")
    private Badges badges = new Badges();

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Badges {
        @JsonProperty("votes")
        private int votes;

        @JsonProperty("attachmentsByType")
        private AttachmentsByType attachments = new AttachmentsByType();

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        private static class AttachmentsByType{
            @JsonProperty("trello")
            private Trello trello = new Trello();

            @Data
            @JsonIgnoreProperties(ignoreUnknown = true)
            private static class Trello {
                @JsonProperty("board")
                private int board;

                @JsonProperty("card")
                private int card;
            }
        }
    }
    */
}
