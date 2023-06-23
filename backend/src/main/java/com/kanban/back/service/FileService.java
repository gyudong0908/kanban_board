package com.kanban.back.service;

import com.kanban.back.Exception.FileStorageException;
import com.kanban.back.Exception.MyFileNotFoundException;
import com.kanban.back.Exception.NotFindData;
import com.kanban.back.dto.reponseDTO.detailpageDTO.FilesDetailDTO;
import com.kanban.back.dto.requestDTO.CardReqDTO;
import com.kanban.back.dto.requestDTO.FilesReqDTO;
import com.kanban.back.entity.Card;
import com.kanban.back.repository.CardRepository;
import com.kanban.back.repository.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

@Service
public class FileService {
    private final Path fileStorageLocation = Paths.get("C:/Users/upload");
    @Autowired
    FilesRepository filesRepository;
    @Autowired
    CardRepository cardRepository;

    @Transactional
    public String storeFile(MultipartFile file, Integer c_id) {
        Card card = cardRepository.getById(c_id);
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMMddhhmmss_");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timeStamp = sdf.format(timestamp);
        Path path = this.fileStorageLocation.resolve(card.getBoard().getB_id().toString()).resolve(c_id.toString());
        // 폴더 만드는 코드
        try {
            Files.createDirectories(path);
        } catch (Exception ex) {
            throw new FileStorageException("폴더를 생성할 수 없습니다.", ex);
        }

        String fileName = timeStamp + StringUtils.cleanPath(file.getOriginalFilename());

        //확장자만 추출하는 형태 ex) exe , png, jpg ...
        String fileExt = fileName.replaceAll("^.*\\.(.*)$", "$1");
        // File DB에 저장
        String filepath = path.toString();
        Long fileSize = file.getSize();
        FilesReqDTO filesReqDTO = new FilesReqDTO();
        filesReqDTO.setFile_original_name(file.getOriginalFilename());
        filesReqDTO.setFile_name(fileName);
        filesReqDTO.setFile_path(filepath);
        filesReqDTO.setFile_ext(fileExt);
        filesReqDTO.setFile_size(fileSize);
        filesReqDTO.setC_id(card.getC_id());
        filesRepository.save(filesReqDTO.toEntity());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("잘못된 파일 경로 입니다" + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = path.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException(fileName + "파일을 저장할 수 없습니다 다시 시도해 주십쇼", ex);
        }
    }

    public Resource loadFileAsResource(Integer file_id) {
        com.kanban.back.entity.Files files = filesRepository.getById(file_id);
        String fileName = files.getFile_name();
        try {
            Path filePath = this.fileStorageLocation.resolve(files.getCard().getBoard().getB_id().toString()).resolve(files.getCard().getC_id().toString()).resolve(fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("파일을 찾을 수 없습니다" + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("파일을 찾을 수 없습니다" + fileName, ex);
        }
    }

    @Transactional
    public void deleteFile(Integer file_id){
            filesRepository.deleteById(file_id);
    }
    @Transactional
    public FilesDetailDTO restoreFile(Integer file_id){
        filesRepository.restoreFile(file_id);
        com.kanban.back.entity.Files files = filesRepository.getById(file_id);
            return files.toDetailDTO();
    }
    @Transactional
    public void hardDeleteFile() throws IOException{
        List<com.kanban.back.entity.Files> files = filesRepository.hardDeleteFile();
        for (com.kanban.back.entity.Files deleteFile : files) {
            Path filePath = Paths.get(deleteFile.getFile_path() + "\\" + deleteFile.getFile_name());
            Files.delete(filePath);
        }
        filesRepository.hardDeleteFileTable();
    }
}