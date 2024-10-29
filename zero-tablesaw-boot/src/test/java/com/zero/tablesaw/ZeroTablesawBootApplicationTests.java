package com.zero.tablesaw;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.BubblePlot;
import tech.tablesaw.plotly.components.Figure;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@SpringBootTest
class ZeroTablesawBootApplicationTests {

    @BeforeEach
    public void before() throws IOException {
        log.info("init some data");
        tornadoes = Table.read().csv("./src/main/resources/data/tornadoes_1950-2014.csv");
    }

    @AfterEach
    public void after() {
        log.info("clean some data");
    }

    @Resource
    private JdbcTemplate jdbcTemplate;

    Table tornadoes;

    @Test
    void contextLoads() throws IOException {

        Table wines = Table.read().csv("./src/main/resources/data/test_wines.csv");

        Table champagne =
                wines.where(
                        wines
                                .stringColumn("wine type")
                                .isEqualTo("Champagne & Sparkling")
                                .and(wines.stringColumn("region").isEqualTo("California")));

        Figure figure =
                BubblePlot.create(
                        "Average retail price for champagnes by year and rating",
                        champagne, // table namex
                        "highest pro score", // x variable column name
                        "year", // y variable column name
                        "Mean Retail" // bubble size
                );

        Plot.show(figure);
    }

    @Test
    public void execute() {
        log.info("your method test Code");
    }

    @Test
    public void columnNames() throws IOException {
        System.out.println(tornadoes.columnNames());
    }

    @Test
    public void shape() throws IOException {
        System.out.println(tornadoes.shape());
    }

    @Test
    public void structure() throws IOException {
        System.out.println(tornadoes.structure().printAll());
    }

    @Test
    public void show() throws IOException {
        System.out.println(tornadoes);
    }

    @Test
    public void structureFilter() throws IOException {
        System.out.println(tornadoes
                .structure()
                .where(tornadoes.structure().stringColumn("Column Type").isEqualTo("DOUBLE")));

    }

    @Test
    public void previewData() throws IOException {
        System.out.println(tornadoes.first(3));
    }

    @Test
    public void ColumnOperate() throws IOException {
        StringColumn month = tornadoes.dateColumn("Date").month();
        tornadoes.addColumns(month);
        System.out.println(tornadoes.first(3));
        tornadoes.removeColumns("State No");
        System.out.println(tornadoes.first(3));
    }

    @Test
    public void sort() throws IOException {
        tornadoes.sortOn("-Fatalities");
        System.out.println(tornadoes.first(20));
    }

    @Test
    public void summary() throws IOException {
        System.out.println(tornadoes.column("Fatalities").summary().print());
    }

    @Test
    public void filter() throws IOException {
        Table result = tornadoes.where(tornadoes.intColumn("Fatalities").isGreaterThan(0));
        result = tornadoes.where(result.dateColumn("Date").isInApril());
        result =
                tornadoes.where(
                        result
                                .intColumn("Width")
                                .isGreaterThan(300) // 300 yards
                                .or(result.doubleColumn("Length").isGreaterThan(10))); // 10 miles

        result = result.select("State", "Date");
        System.out.println(result);
    }

    @Test
    public void write() throws IOException {
        tornadoes.write().csv("rev_tornadoes_1950-2014-test.csv");
    }

    @Test
    public void dataFromMysql() throws IOException {
        Table table = jdbcTemplate.query("SELECT  user_id,username,age from system_user", new ResultSetExtractor<Table>() {
            @Override
            public Table extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return Table.read().db(resultSet);
            }
        });
        System.out.println(table);
    }

}
