package indi.jcodec;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
//import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.api.specific.AVCMP4Adaptor;
import org.jcodec.codecs.h264.H264Decoder;
import org.jcodec.codecs.h264.mp4.AvcCBox;
import org.jcodec.common.Codec;
import org.jcodec.common.DemuxerTrack;
import org.jcodec.common.DemuxerTrackMeta;
import org.jcodec.common.io.ByteBufferSeekableByteChannel;
import org.jcodec.common.io.FileChannelWrapper;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Packet;
import org.jcodec.common.model.Picture;
import org.jcodec.containers.flv.FLVMetadata;
import org.jcodec.containers.flv.FLVReader;
import org.jcodec.containers.flv.FLVTag;
import org.jcodec.containers.flv.FLVTag.AacAudioTagHeader;
import org.jcodec.containers.flv.FLVTag.AvcVideoTagHeader;
import org.jcodec.containers.flv.FLVTag.TagHeader;
import org.jcodec.containers.flv.FLVTag.Type;
import org.jcodec.containers.flv.FLVTrackDemuxer;
import org.jcodec.containers.flv.FLVTrackDemuxer.FLVDemuxerTrack;
import org.jcodec.containers.mp4.boxes.AVC1Box;
import org.jcodec.scale.AWTUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.google.common.collect.HashMultiset;

import indi.util.FileUtils;
import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class SimpleTest {

    /**
     * test .mp4
     */
    @Test
    @Disabled
    void grabMp4() throws FileNotFoundException, IOException, JCodecException, InterruptedException {
        File file = new File("E:\\电影\\声之形.mp4");
        FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(file));
        Picture picture = grab.getNativeFrame();
        System.out.println(picture.getWidth() + "x" + picture.getHeight() + " " + picture.getColor());
        // 将Picture转化为awt图像
        BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
        ImageIO.write(bufferedImage, "png", new File("e:/frame0.png"));
    }

    /**
     * 测试截取FLV文件的特定帧图像
     * 
     * @throws IOException
     * @throws JCodecException 
     */
    @Test
    void grabFlv() throws IOException, JCodecException {
        Path sourcePath = Paths.get("e:", "test2.flv");
        Path targetPath = Paths.get("e:", "flvTest");
        FileUtils.createDirectoryIfNotExist(targetPath);
        FileChannelWrapper channel = new FileChannelWrapper(FileChannel.open(sourcePath, StandardOpenOption.READ));
        ByteBuffer flvBb = ByteBuffer.wrap(Files.readAllBytes(sourcePath));
        System.out.println(FLVReader.probe(flvBb));
        
        FLVReader flvReader = new FLVReader(channel);
        
        // FLV Header

        // FLV Tag
        HashMultiset<FLVTag.Type> multiset = HashMultiset.create();
        FLVTag flvTag = null;
        while ((flvTag = flvReader.readNextPacket()) != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(flvTag.getFrameNo()).append(" ");
            
            // tag header
            // tag body
            ByteBuffer data = flvTag.getData();
            Type type = flvTag.getType();
            multiset.add(type);
            TagHeader tagHeader = flvTag.getTagHeader();
            switch (type) {
            case SCRIPT:
                sb.append("script");
//                System.out.println("script" + tagHeader);
                break;
            case AUDIO:
                sb.append("audio");
//                System.out.println("audio" + tagHeader);
                break;
            case VIDEO:
                sb.append("video");
//                System.out.println("video" + tagHeader);
                if (tagHeader instanceof AvcVideoTagHeader) {
                    AvcVideoTagHeader videoTagHeader = (AvcVideoTagHeader) tagHeader;
                    int frameType = videoTagHeader.getFrameType();
                    sb.append("-").append(frameType);
                    System.out.println(frameType + "- " + videoTagHeader.getAvcPacketType());
                    System.out.println(videoTagHeader.getCodec());
                    System.out.println(H264Decoder.probe(data));
                }
                
                // try parse frame
                break;
            default:
                break;
            }

            Path path = targetPath.resolve(sb.toString());
            FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            fileChannel.write(data);
        }
        
        System.out.println(multiset);
    }
}
