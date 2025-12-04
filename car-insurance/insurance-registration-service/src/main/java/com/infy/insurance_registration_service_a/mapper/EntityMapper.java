package com.infy.insurance_registration_service_a.mapper;

/**
 * Generic Entity Mapper interface
 * Defines standard mapping operations between Entity and DTO
 *
 * @param <E> Entity type
 * @param <D> DTO type
 */
public interface EntityMapper<E, D> {

    /**
     * Convert DTO to Entity
     *
     * @param dto DTO object
     * @return Entity object
     */
    E toEntity(D dto);

    /**
     * Convert Entity to DTO
     *
     * @param entity Entity object
     * @return DTO object
     */
    D toDTO(E entity);

    /**
     * Update existing entity with values from DTO
     *
     * @param dto    DTO object with new values
     * @param entity Entity object to update
     */
    void updateEntityFromDTO(D dto, E entity);
}
