package dao.Ov_chipkaart;

import domein.Ov_chipkaart;
import domein.Reiziger;

import java.util.List;

public interface Ov_chipkaartDAO {

    public boolean save(Ov_chipkaart ov_chipkaart);

    public boolean update(Ov_chipkaart ov_chipkaart);

    public boolean delete(Ov_chipkaart ov_chipkaart);

    public Ov_chipkaart findById(int id);

    public List<Ov_chipkaart> findByReiziger(Reiziger reiziger);

    public List<Ov_chipkaart> findAll();
}
