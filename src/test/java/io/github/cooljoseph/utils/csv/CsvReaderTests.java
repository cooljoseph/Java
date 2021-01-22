package io.github.cooljoseph.utils.csv;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CsvReaderTests {

    @Test
    void test() {
        List<CsvRecord> records = CsvReader.read("data/test.csv", recorder -> CsvRecord.of(Integer.valueOf(recorder[0]), recorder[1]));

        assertThat(records).isNotNull();
        assertThat(records.size()).isGreaterThan(0);

        records.forEach(System.out::println);
    }

    private static class CsvRecord {
        Integer id;
        String name;

        private CsvRecord(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public static CsvRecord of(Integer id, String name) {
            return new CsvRecord(id, name);
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "CsvRecord{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}