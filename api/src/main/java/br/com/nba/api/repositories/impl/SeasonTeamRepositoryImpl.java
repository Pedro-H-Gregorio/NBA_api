package br.com.nba.api.repositories.impl;

import br.com.nba.api.entities.SeasonTeam;
import br.com.nba.api.repositories.PersistenciaDawException;
import br.com.nba.api.repositories.interfaces.SeasonTeamRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeasonTeamRepositoryImpl extends RepositoryBaseImpl<SeasonTeam, String> implements SeasonTeamRepository {
    public SeasonTeamRepositoryImpl() {
        super(SeasonTeam.class);
    }

    @Override
    protected String getEntityName() {
        return "SEASON_TEAM";
    }

    public List<SeasonTeam> getAllTeamsBySeason(String seasonId) throws PersistenciaDawException {
        try(EntityManager em = getEntityManager()) {
            try {
//                em.createNamedQuery("SeasonTeam.findAllTeamsBySeason", SeasonTeam.class)
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT obj FROM ").append(getEntityName()).append(" obj WHERE obj.season.id = '").append(seasonId).append("'");
                TypedQuery<SeasonTeam> query = em.createQuery(sql.toString(), SeasonTeam.class);
                return query.getResultList();
            } catch (PersistenceException pe) {
                pe.printStackTrace();
                throw new PersistenciaDawException("Ocorreu algum erro ao tentar recuperar todas as instâncias da entidade.", pe);
            }
        }
    }
}
