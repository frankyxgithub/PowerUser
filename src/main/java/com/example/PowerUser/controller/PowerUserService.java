package com.example.PowerUser.controller;

import com.example.PowerUser.exception.UserException;
import com.example.PowerUser.model.PowerUser;
import com.example.PowerUser.repository.PowerUserRepository;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.Metamodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class PowerUserService {

    @Autowired
    private PowerUserRepository userRepository;

    @Cacheable("allPowerUsers")
    public List<PowerUser> getAllPowerUsers(){
        return userRepository.findAll();
    }

    @CacheEvict(value = "allPowerUsers", allEntries = true)
    public ResponseEntity<PowerUser> addUser(PowerUser powerUser){
        userRepository.save(powerUser);
        return ResponseEntity.ok(powerUser);
    }


    /*
        @GetMapping("/error")
        public ResponseEntity<String> error(HttpStatus httpStatus){

            if(HttpStatus.NOT_FOUND.is3xxRedirection()){
                return ResponseEntity.badRequest().body(new GlobalExceptions()
                                .handleResourceNotFound(new ResourceNotFoundException()))
                        .getBody();
            }
            if(HttpStatus.INTERNAL_SERVER_ERROR.is4xxClientError()){
                return ResponseEntity.badRequest().body(new GlobalExceptions()
                                .handleExceptions(new Exception()))
                        .getBody();
            }
            return ResponseEntity.internalServerError().body(new GlobalExceptions()
                            .handleResourceNotFound(new ResourceNotFoundException()))
                    .getBody();

        }

        @GetMapping("/errors")
        public ResponseEntity<String> notFoundHandler(){
            return ResponseEntity.badRequest().body(new GlobalExceptions()
                            .handleResourceNotFound(new ResourceNotFoundException()))
                    .getBody();
        }
    */
    @Cacheable(value = "getUserById", key = "#id")
    public ResponseEntity<PowerUser> getPowerUser(Integer id){
        PowerUser user = userRepository.findById(id).orElseThrow(() -> new UserException("User not found"));
        return ResponseEntity.ok(user);
    }

    @Cacheable(value = "getUserByEmail", key = "#email")
    public ResponseEntity<PowerUser> getPowerUserByEmail(String email){
        PowerUser user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
        return ResponseEntity.ok(user);
    }

    @Cacheable(value = "getUserByFullName", key = "#fullName")
    public ResponseEntity<PowerUser> getPowerUserByFullName(String fullName){
        PowerUser user = userRepository.findByFullName(fullName).orElseThrow(() -> new UserException("User not found"));
        return ResponseEntity.ok(user);
    }

    @CacheEvict(value = {"getUserById", "getUserByEmail", "getUserByFullName"}, key = "#id")
    public ResponseEntity<String> updatePowerUser(Integer id,PowerUser powerUser ){
        PowerUser user = userRepository.findById(id).orElseThrow(() -> new UserException("User not found"));

        user.setFullName(powerUser.getFullName());
        user.setAddress(powerUser.getAddress());
        user.setAge(powerUser.getAge());
        user.setEmail(powerUser.getEmail());

        userRepository.save(user);

        EntityManager entityManager = new EntityManager() {
            @Override
            public void persist(Object entity) {

            }

            @Override
            public <T> T merge(T entity) {
                return null;
            }

            @Override
            public void remove(Object entity) {

            }

            @Override
            public <T> T find(Class<T> entityClass, Object primaryKey) {
                return null;
            }

            @Override
            public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties) {
                return null;
            }

            @Override
            public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode) {
                return null;
            }

            @Override
            public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, Map<String, Object> properties) {
                return null;
            }

            @Override
            public <T> T getReference(Class<T> entityClass, Object primaryKey) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public void setFlushMode(FlushModeType flushMode) {

            }

            @Override
            public FlushModeType getFlushMode() {
                return null;
            }

            @Override
            public void lock(Object entity, LockModeType lockMode) {

            }

            @Override
            public void lock(Object entity, LockModeType lockMode, Map<String, Object> properties) {

            }

            @Override
            public void refresh(Object entity) {

            }

            @Override
            public void refresh(Object entity, Map<String, Object> properties) {

            }

            @Override
            public void refresh(Object entity, LockModeType lockMode) {

            }

            @Override
            public void refresh(Object entity, LockModeType lockMode, Map<String, Object> properties) {

            }

            @Override
            public void clear() {

            }

            @Override
            public void detach(Object entity) {

            }

            @Override
            public boolean contains(Object entity) {
                return false;
            }

            @Override
            public LockModeType getLockMode(Object entity) {
                return null;
            }

            @Override
            public void setProperty(String propertyName, Object value) {

            }

            @Override
            public Map<String, Object> getProperties() {
                return null;
            }

            @Override
            public Query createQuery(String qlString) {
                return null;
            }

            @Override
            public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
                return null;
            }

            @Override
            public Query createQuery(CriteriaUpdate updateQuery) {
                return null;
            }

            @Override
            public Query createQuery(CriteriaDelete deleteQuery) {
                return null;
            }

            @Override
            public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass) {
                return null;
            }

            @Override
            public Query createNamedQuery(String name) {
                return null;
            }

            @Override
            public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
                return null;
            }

            @Override
            public Query createNativeQuery(String sqlString) {
                return null;
            }

            @Override
            public Query createNativeQuery(String sqlString, Class resultClass) {
                return null;
            }

            @Override
            public Query createNativeQuery(String sqlString, String resultSetMapping) {
                return null;
            }

            @Override
            public StoredProcedureQuery createNamedStoredProcedureQuery(String name) {
                return null;
            }

            @Override
            public StoredProcedureQuery createStoredProcedureQuery(String procedureName) {
                return null;
            }

            @Override
            public StoredProcedureQuery createStoredProcedureQuery(String procedureName, Class... resultClasses) {
                return null;
            }

            @Override
            public StoredProcedureQuery createStoredProcedureQuery(String procedureName, String... resultSetMappings) {
                return null;
            }

            @Override
            public void joinTransaction() {

            }

            @Override
            public boolean isJoinedToTransaction() {
                return false;
            }

            @Override
            public <T> T unwrap(Class<T> cls) {
                return null;
            }

            @Override
            public Object getDelegate() {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public boolean isOpen() {
                return false;
            }

            @Override
            public EntityTransaction getTransaction() {
                return null;
            }

            @Override
            public EntityManagerFactory getEntityManagerFactory() {
                return null;
            }

            @Override
            public CriteriaBuilder getCriteriaBuilder() {
                return null;
            }

            @Override
            public Metamodel getMetamodel() {
                return null;
            }

            @Override
            public <T> EntityGraph<T> createEntityGraph(Class<T> rootType) {
                return null;
            }

            @Override
            public EntityGraph<?> createEntityGraph(String graphName) {
                return null;
            }

            @Override
            public EntityGraph<?> getEntityGraph(String graphName) {
                return null;
            }

            @Override
            public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> entityClass) {
                return null;
            }
        };
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PowerUser> userQuery = builder.createQuery(PowerUser.class);
        Root<PowerUser> userRoot = userQuery.from(PowerUser.class);
        userQuery.select(userRoot).where(builder.gt(userRoot.get("age"), 25));
        TypedQuery<PowerUser> typedQuery = entityManager.createQuery(userQuery);
        assert typedQuery != null;
        List<PowerUser> users = typedQuery.getResultList();

        return ResponseEntity.ok("User successfully updated");

    }

    @CacheEvict(value = "getUserById", key = "#id")
    public ResponseEntity<String> deletePowerUser(Integer id){
        userRepository.deleteById(id);
        return ResponseEntity.ok("User successfully deleted");
    }
}
