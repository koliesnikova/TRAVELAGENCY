package sk.upjs.storage;

import sk.upjs.entity.Clients;

import sk.upjs.exeption.EntityNotFoundException;
import sk.upjs.exeption.EntityUndeletableException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import sk.upjs.storage.dao.ClientsDAO;
import sk.upjs.storage.dao.PredajDAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysqlClientsDao implements ClientsDAO{
    private JdbcTemplate jdbcTemplate;

    public MysqlClientsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Clients> getAll() {
        return jdbcTemplate.query("SELECT id,meno,priezvisko,dat_nar,adressa,cislo FROM clients", new ClientsRowMapper());

    }

    @Override
    public Clients getById(long id) throws EntityNotFoundException {
        try {
            return jdbcTemplate.queryForObject("SELECT id,meno,priezvisko,dat_nar,adressa,cislo FROM clients WHERE id =" + id, new ClientsRowMapper());
        } catch (DataAccessException e) {
            throw new EntityNotFoundException("Client with id " + id + " not found");

        }
    }

    @Override
    public Clients save(Clients clients) throws EntityNotFoundException {
        if (clients == null) {
            return null;
        }
        if (clients.getId() == null) {//insert
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
            insert.withTableName("clients");
            insert.usingGeneratedKeyColumns("id");
            insert.usingColumns("meno", "priezvisko", "dat_nar", "adressa", "cislo");
            Map<String, Object> valuesMap = new HashMap<>();
            valuesMap.put("meno", clients.getMeno());
            valuesMap.put("priezvisko", clients.getPriezvisko());
            valuesMap.put("dat_nar", clients.getDat_nar());
            valuesMap.put("adressa", clients.getAdressa());
            valuesMap.put("cislo", clients.getCislo());

            return new Clients(
                    insert.executeAndReturnKey(valuesMap).longValue(),
                    clients.getMeno(),
                    clients.getPriezvisko(),
                    clients.getDat_nar(),
                    clients.getAdressa(),
                    clients.getCislo()
            );
        } else { //update

            String sql = "UPDATE clients SET meno = ?, priezvisko = ?,dat_nar = ?, adressa = ?, cislo = ? WHERE id = ?";

            int changed = jdbcTemplate.update(sql, clients.getMeno(), clients.getPriezvisko(), clients.getDat_nar(), clients.getAdressa(), clients.getCislo(),clients.getId());

            if (changed == 1) {
                return clients;
            } else {
                throw new EntityNotFoundException("Client with id" + clients.getId() + "not found");
            }
        }


    }

    public Clients delete(long id) throws EntityNotFoundException, EntityUndeletableException {
        Clients clients = getById(id);
        PredajDAO predajDAO = DaoFactory.INSTANCE.getPredajDAO();
        predajDAO.deleteAllByClient(clients);

        try {

            jdbcTemplate.update("DELETE FROM clients WHERE id = " + id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityUndeletableException(
                    "Client " + clients + " is a part of some predaj, cannot be deleted", e);
        }
        return clients;
    }
    @Override
    public Clients idByName(String name, String priezvisko) {
        //try {

        String sql = "Select id from clients where meno = ? and priezvisko=? ";
        System.out.println(name);
        return jdbcTemplate.queryForObject( sql, new TourRowMapper2(),name,priezvisko);

        //   } catch (DataAccessException e) {
        //     throw new EntityNotFoundException("empty for refresh");

        //}
    }

    private class ClientsRowMapper implements RowMapper<Clients> {
        @Override
        public Clients mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String meno = rs.getString("meno");
            String priezvisko = rs.getString("priezvisko");
            Date dat_nar = rs.getDate("dat_nar");
            String adressa = rs.getString("adressa");
            String cislo = rs.getString("cislo");
            return new Clients(id, meno, priezvisko, dat_nar, adressa, cislo);
        }

    }
    private class TourRowMapper2 implements RowMapper<Clients> {
        @Override
        public Clients mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            return new Clients(id);
        }

    }
}

