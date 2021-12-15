package storage;

import entity.Clients;
import exeption.EntityNotFoundException;
import exeption.EntityUndeletableException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import storage.dao.ClientsDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysqlClientsDao implements ClientsDAO {
    private JdbcTemplate jdbcTemplate;

    public MysqlClientsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Clients> getAll() {
        return jdbcTemplate.query("SELECT id,meno,priezvisko,dat_nar,adressa,cislo FROM clients", new RowMapper<Clients>() {
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
        });
    }


    public Clients getById(long id) throws EntityNotFoundException {
        try {
            return jdbcTemplate.queryForObject("SELECT id,meno,priezvisko,dat_nar,adressa,cislo FROM clients WHERE id =" + id, new RowMapper<Clients>() {

                public Clients mapRow(ResultSet rs, int rowNum) throws SQLException {
                    long id = rs.getLong("id");
                    String meno = rs.getString("meno");
                    String priezvisko = rs.getString("priezvisko");
                    Date dat_nar = rs.getDate("dat_nar");
                    String adressa = rs.getString("adressa");
                    String cislo = rs.getString("cislo");
                    return new Clients(id, meno, priezvisko, dat_nar, adressa, cislo);
                }

            });
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

            String sql = "UPDATE clients SET meno = ?, priezvisko = ?,dat_nar = ?, adressa = ?, cislo = ? WHERE id = 1 ";
            int changed = jdbcTemplate.update(sql, clients.getMeno(), clients.getPriezvisko(), clients.getDat_nar(), clients.getAdressa(), clients.getCislo());
            if (changed == 1) {
                return clients;
            } else {
                throw new EntityNotFoundException("Client with id" + clients.getId() + "not found");
            }
        }


    }

    public Clients delete(long id) throws EntityNotFoundException, EntityUndeletableException {
        Clients clients = getById(id);

        try {

            jdbcTemplate.update("DELETE FROM clients WHERE id = " + id);
        } catch (DataIntegrityViolationException e) {
            		throw new EntityUndeletableException(
            				"Client " + clients + " is a part of some predaj, cannot be deleted", e);
            		}
            return clients;
        }
    }

