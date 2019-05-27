package enn.testone.repository;

import enn.testone.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ArticleRepository extends ElasticsearchRepository<Book,Integer> {//Book实体类型  Integer索引id类型
    //自定义方法 会根据需求去ES中查询
    public List<Book> findBookByBookNameLike(String bookName);
}
