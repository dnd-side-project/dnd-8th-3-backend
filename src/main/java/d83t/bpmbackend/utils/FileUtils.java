package d83t.bpmbackend.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.UUID;

@Component
@Slf4j
public class FileUtils {

    /*
     * 로컬에 파일 저장할때 그 경로를 리턴합니다.
     */
    public static String getUploadPath() {
        String path = new File("").getAbsolutePath() + "/" + "bodyShapes/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        return path;
    }

    /*
     * 파일 이름 중복을 고려하여, 랜덤한 이름으로 변환합니다.
     */
    public static String createNewFileName(String originalName) {
        return UUID.randomUUID() + "." + originalName.substring(originalName.lastIndexOf("."));
    }

    /*
     * 로컬 파일을 삭제합니다.
     */

    public static void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("file delete success");
            return;
        }
        log.info("file delete fail");
    }

}
