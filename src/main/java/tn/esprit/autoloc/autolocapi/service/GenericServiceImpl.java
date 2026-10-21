package tn.esprit.autoloc.autolocapi.service;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;

import java.util.List;

public abstract class GenericServiceImpl<T, ID> implements IGenericService<T, ID> {

    protected final JpaRepository<T, ID> repository;

    protected GenericServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    /** Retourne le nom de l'entité (pour les messages d'erreur). */
    protected abstract String getEntityName();

    /** Retourne l'identifiant d'une entité. */
    protected abstract ID getId(T entity);

    /** Copie les champs de 'source' vers 'target'. */
    protected abstract void setFields(T source, T target);

    @Override
    public T create(T entity) {
        if (getId(entity) != null) {
            throw new IllegalArgumentException(
                "L'identifiant doit être nul pour une création");
        }
        if (!check(entity, true)) {
            throw new IllegalArgumentException(
                "Les données de " + getEntityName() + " sont invalides");
        }
        return repository.save(entity);
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                getEntityName(), String.valueOf(id)));
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T update(ID id, T entity) {
        T existing = findById(id);
        if (!check(entity, false)) {
            throw new IllegalArgumentException(
                "Les données de " + getEntityName() + " sont invalides");
        }
        setFields(entity, existing);
        return repository.save(existing);
    }

    @Override
    public void deleteById(ID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(
                getEntityName(), String.valueOf(id));
        }
        repository.deleteById(id);
    }
}
