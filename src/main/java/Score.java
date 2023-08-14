import com.google.gson.*;

import java.util.Map;

public class Score {
    private final String formString;
    private final Answers answers = parseForm();

    public Score(String formString) {
        this.formString = formString;
    }

    private Answers parseForm() {
        assert formString != null;
        JsonObject convertedForm = JsonParser
                .parseString(formString)
                .getAsJsonObject();
        return new Answers(
                convertedForm.get("TRL").getAsString(),
                convertedForm.get("CRL").getAsString(),
                convertedForm.get("HRL").getAsString(),
                convertedForm.get("ERL").getAsString()
        );
    }

//    private int TRLScore, CRLScore, HRLScore, ERLScore;

    private int countTRLScore() {
        int TRLScore = 0;
        switch (answers.getTRL()) {
            case "идет увеличение производственных мощностей или расширение ассортимента":
                TRLScore += 5;
                break;
            case "достигнута плановая производственная мощность":
                TRLScore += 4;
                break;
            case "идет массовый выпуск":
                TRLScore += 3;
                break;
            case "есть коммерческий продукт":
                TRLScore += 2;
                break;
            case "есть прототип / продукт с базовыми (ключевыми) функциями":
                TRLScore += 1;
                break;
            case "есть концепция продукта, определена полезность и реализуемость":
                TRLScore += 0;
                break;
        }
        return TRLScore;
    }

    private int countCRLScore() {
        int CRLScore = 0;
        switch (answers.getCRL()) {
            case "есть узнаваемый бренд, идет выход на новые рынки":
                CRLScore += 5;
                break;
            case "достигнут плановый объем рынка (SOM)":
                CRLScore += 4;
                break;
            case "идут системные продажи":
                CRLScore += 3;
                break;
            case "идут несистемные продажи, отработка замечаний":
                CRLScore += 2;
                break;
            case "сформулированы характеристики продукта и ценовая политика, определены покупатели и поставщики":
                CRLScore += 1;
                break;
            case "определен объем и динамика рынка (SAM, SOM), целевая аудитория, есть конкурентный анализ":
                CRLScore += 0;
                break;
        }
        return CRLScore;
    }

    private int countHRLScore() {
        int HRLScore = 0;
        switch (answers.getHRL()) {
            case "есть система стратегического планирования и управления":
                HRLScore += 5;
                break;
            case "есть система сбалансированных показателей и мотивации":
                HRLScore += 4;
                break;
            case "есть система документооборота, в тч оформлены трудовые отношения":
                HRLScore += 3;
                break;
            case "есть регламенты бизнес-процессов":
                HRLScore += 2;
                break;
            case "есть команда":
                HRLScore += 1;
                break;
            case "нет команды":
                HRLScore += 0;
                break;
        }
        return HRLScore;
    }

    private int countERLScore() {
        int ERLScore = 0;
        switch (answers.getERL()) {
            case "идет рост стоимости бизнеса (активов)":
                ERLScore += 5;
                break;
            case "идет распределение прибыли, в тч окупаемость инвестиций":
                ERLScore += 4;
                break;
            case "достигнута точка безубыточности":
                ERLScore += 3;
                break;
            case "есть фактические доходы и расходы":
                ERLScore += 2;
                break;
            case "есть фактические расходы":
                ERLScore += 1;
                break;
            case "есть финансовая модель":
                ERLScore += 0;
                break;
        }
        return ERLScore;
    }

    public int countFinalScore() {
        int score = 0;
        score += countTRLScore();
        score += countCRLScore();
        score += countHRLScore();
        score += countERLScore();
        return score;
    }

    static class Answers {
        private final String TRL, CRL, HRL, ERL;

        public Answers(String TRL, String CRL, String HRL, String ERL) {
            this.TRL = TRL;
            this.CRL = CRL;
            this.HRL = HRL;
            this.ERL = ERL;
        }

        public String getTRL() {
            return TRL;
        }

        public String getCRL() {
            return CRL;
        }

        public String getHRL() {
            return HRL;
        }

        public String getERL() {
            return ERL;
        }
    }
}