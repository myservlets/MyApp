package seven.team.sqlite;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/3/23 0023.
 */
public class Province extends DataSupport implements Serializable {
    private String code;
    private String name;
    private List<Country> cityList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Country> getCityList() {
        return cityList;
    }

    public void setCityList(List<Country> cityList) {
        this.cityList = cityList;
    }

    class Country{
        private String code;
        private String name;
        private List<City>areaList;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<City> getCities() {
            return areaList;
        }

        public void setCities(List<City> cities) {
            this.areaList = cities;
        }


        class City{
            private String code;
            private String name;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
