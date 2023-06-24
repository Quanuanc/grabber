package cheng.grabber.service;

import cheng.grabber.domain.Keyword;
import cheng.grabber.repo.KeywordRepository;
import org.springframework.stereotype.Service;

@Service
public class KeywordService {

    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    private final KeywordRepository keywordRepository;


    public Keyword saveKeyword(Keyword keyword) {
        return keywordRepository.save(keyword);
    }

    public void deleteKeyword(Integer id) {
        keywordRepository.deleteById(id);
    }

    public Keyword findKeyword(Integer id) {
        return keywordRepository.findById(id).orElse(null);
    }

    public Iterable<Keyword> findAllKeywords() {
        return keywordRepository.findAll();
    }
}
