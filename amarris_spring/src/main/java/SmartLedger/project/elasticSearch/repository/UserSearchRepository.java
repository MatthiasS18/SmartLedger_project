package SmartLedger.project.elasticSearch.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import SmartLedger.project.elasticSearch.document.UserDocument;


/**
 * Elasticsearch repository for User.
 * Provides full-text search capabilities on users.
 * Used by UserSERVICE to search users by username or email.
 */
public interface UserSearchRepository extends ElasticsearchRepository<UserDocument, String> {
    List<UserDocument> findByUsernameContainingOrEmailContaining(
        String username, String email
    );
}