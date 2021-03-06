package org.mockserver.client.serialization.serializers.body;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.mockserver.mappers.ContentTypeMapper;
import org.mockserver.model.JsonBody;

import java.io.IOException;

/**
 * @author jamesdbloom
 */
public class JsonBodySerializer extends StdSerializer<JsonBody> {

    public JsonBodySerializer() {
        super(JsonBody.class);
    }

    @Override
    public void serialize(JsonBody jsonBody, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        if (jsonBody.getNot() != null && jsonBody.getNot()) {
            jgen.writeBooleanField("not", jsonBody.getNot());
        }
        if (jsonBody.getCharset() != null && !jsonBody.getCharset().equals(ContentTypeMapper.DEFAULT_HTTP_CHARACTER_SET)) {
            jgen.writeStringField("charset", jsonBody.getCharset().name());
        }
        jgen.writeStringField("type", jsonBody.getType().name());
        jgen.writeStringField("json", jsonBody.getValue());
        if (jsonBody.getMatchType() != JsonBody.DEFAULT_MATCH_TYPE) {
            jgen.writeStringField("matchType", jsonBody.getMatchType().name());
        }
        jgen.writeEndObject();
    }
}
