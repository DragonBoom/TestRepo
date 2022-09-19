/**
 * 
 */
package indi.jaudiotagger;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.datatype.Artwork;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.test.TestSeparateExtension;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wzh
 * @since 2020.09.20
 */
@ExtendWith(TestSeparateExtension.class)
@Slf4j
class JaudiotaggerTest {
    
    // 关闭jaudiotagger自带的日志（大多数日志完全没意义）
    {
        // 初始化类的静态变量
        try {
            Class.forName("org.jaudiotagger.audio.AudioFileIO");
            Class.forName("org.jaudiotagger.tag.id3.AbstractID3v2Tag");
            Class.forName("org.jaudiotagger.tag.datatype.NumberFixedLength");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 设置日志级别
        Logger.getLogger("org.jaudiotagger.tag.datatype").setLevel(Level.OFF);
        Logger.getLogger("org.jaudiotagger.tag.id3").setLevel(Level.OFF);
        Logger.getLogger("org.jaudiotagger.audio").setLevel(Level.OFF);
        Logger.getLogger("org.jaudiotagger.tag.vorbiscomment").setLevel(Level.OFF);
    }

    /**
     * 测试每个标签的ID（可通过一边打印一边修改文件标签测试）
     * 
     * @since 2020.09.20
     */
    @Test
    void testTagId() throws CannotReadException, IOException, TagException, ReadOnlyFileException,
            InvalidAudioFrameException, ClassNotFoundException {
        // AudioFile使用自己的java.util.logging.Logger(可直接修改 AudioFile.logger)
        AudioFile f = AudioFileIO.read(new File("e:\\test.flac"));
        Tag tag = f.getTag();
        // 打印所有标签的类型
        Iterator<TagField> iterator = tag.getFields();
        while (iterator.hasNext()) {
            TagField field = iterator.next();
            log.debug(" {} - {}", field.getId(), field.isEmpty());
            Artwork artwork = tag.getFirstArtwork();// nullable
            log.info("pictureType = {}", artwork.getPictureType());// 3 = front cover, 6 = media(cover)
        }
        
//        TagField
//        tag.setField(field);
//        tag.setField(FieldKey,"Kings of Leon");
//        f.commit();
    }
}
