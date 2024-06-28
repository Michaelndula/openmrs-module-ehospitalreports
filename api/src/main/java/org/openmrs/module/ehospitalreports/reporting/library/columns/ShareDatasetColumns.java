package org.openmrs.module.ehospitalreports.reporting.library.columns;

import org.openmrs.module.ehospitalreports.reporting.library.datasets.eHospitalBaseDataSet;

import java.util.Arrays;
import java.util.List;

public class ShareDatasetColumns {
	
	public static List<eHospitalBaseDataSet.ColumnParameters> getMerGenderAndAgeColumns() {
		eHospitalBaseDataSet.ColumnParameters unknownM = new eHospitalBaseDataSet.ColumnParameters("unknownM", "Unknown age male",
		        "gender=M|age=UK", "UNKM");
		eHospitalBaseDataSet.ColumnParameters under1M = new eHospitalBaseDataSet.ColumnParameters("under1M", "under 1 year male",
		        "gender=M|age=<1", "M1");
		eHospitalBaseDataSet.ColumnParameters oneTo4M = new eHospitalBaseDataSet.ColumnParameters("oneTo4M", "1 - 4 years male",
		        "gender=M|age=1-4", "M2");
		eHospitalBaseDataSet.ColumnParameters fiveTo9M = new eHospitalBaseDataSet.ColumnParameters("fiveTo9M", "5 - 9 years male",
		        "gender=M|age=5-9", "M3");
		eHospitalBaseDataSet.ColumnParameters tenTo14M = new eHospitalBaseDataSet.ColumnParameters("tenTo14M", "10 - 14 male",
		        "gender=M|age=10-14", "M4");
		eHospitalBaseDataSet.ColumnParameters fifteenTo19M = new eHospitalBaseDataSet.ColumnParameters("fifteenTo19M",
		        "15 - 19 male", "gender=M|age=15-19", "M5");
		eHospitalBaseDataSet.ColumnParameters twentyTo24M = new eHospitalBaseDataSet.ColumnParameters("twentyTo24M", "20 - 24 male",
		        "gender=M|age=20-24", "M6");
		eHospitalBaseDataSet.ColumnParameters twenty5To29M = new eHospitalBaseDataSet.ColumnParameters("twenty4To29M",
		        "25 - 29 male", "gender=M|age=25-29", "M7");
		eHospitalBaseDataSet.ColumnParameters thirtyTo34M = new eHospitalBaseDataSet.ColumnParameters("thirtyTo34M", "30 - 34 male",
		        "gender=M|age=30-34", "M8");
		eHospitalBaseDataSet.ColumnParameters thirty5To39M = new eHospitalBaseDataSet.ColumnParameters("thirty5To39M",
		        "35 - 39 male", "gender=M|age=35-39", "M9");
		eHospitalBaseDataSet.ColumnParameters fortyTo44M = new eHospitalBaseDataSet.ColumnParameters("fortyTo44M", "40 - 44 male",
		        "gender=M|age=40-44", "M10");
		eHospitalBaseDataSet.ColumnParameters forty5To49M = new eHospitalBaseDataSet.ColumnParameters("forty5To49M", "45 - 49 male",
		        "gender=M|age=45-49", "M11");
		// 50-54, 55-59, 60-64, 65+ male
		eHospitalBaseDataSet.ColumnParameters fiftyTo54M = new eHospitalBaseDataSet.ColumnParameters("fiftyTo54M", "50 - 54 male",
		        "gender=M|age=50-54", "M12");
		eHospitalBaseDataSet.ColumnParameters fifty5To59M = new eHospitalBaseDataSet.ColumnParameters("fifty5To59M", "55 - 59 male",
		        "gender=M|age=55-59", "M13");
		eHospitalBaseDataSet.ColumnParameters sixtyTo64M = new eHospitalBaseDataSet.ColumnParameters("sixtyTo64M", "60 - 64 male",
		        "gender=M|age=60-64", "M14");
		eHospitalBaseDataSet.ColumnParameters above65M = new eHospitalBaseDataSet.ColumnParameters("above65M", "65+ male",
		        "gender=M|age=65+", "M15");
		
		eHospitalBaseDataSet.ColumnParameters unknownF = new eHospitalBaseDataSet.ColumnParameters("unknownF", "Unknown age female",
		        "gender=F|age=UK", "UNKF");
		eHospitalBaseDataSet.ColumnParameters under1F = new eHospitalBaseDataSet.ColumnParameters("under1F", "under 1 year female",
		        "gender=F|age=<1", "F1");
		eHospitalBaseDataSet.ColumnParameters oneTo4F = new eHospitalBaseDataSet.ColumnParameters("oneTo4F", "1 - 4 years female",
		        "gender=F|age=1-4", "F2");
		eHospitalBaseDataSet.ColumnParameters fiveTo9F = new eHospitalBaseDataSet.ColumnParameters("fiveTo9F", "5 - 9 years female",
		        "gender=F|age=5-9", "F3");
		eHospitalBaseDataSet.ColumnParameters tenTo14F = new eHospitalBaseDataSet.ColumnParameters("tenTo14F", "10 - 14 female",
		        "gender=F|age=10-14", "F4");
		eHospitalBaseDataSet.ColumnParameters fifteenTo19F = new eHospitalBaseDataSet.ColumnParameters("fifteenTo19F",
		        "15 - 19 female", "gender=F|age=15-19", "F5");
		eHospitalBaseDataSet.ColumnParameters twentyTo24F = new eHospitalBaseDataSet.ColumnParameters("twentyTo24F",
		        "20 - 24 female", "gender=F|age=20-24", "F6");
		eHospitalBaseDataSet.ColumnParameters twenty5To29F = new eHospitalBaseDataSet.ColumnParameters("twenty4To29F",
		        "25 - 29 female", "gender=F|age=25-29", "F7");
		eHospitalBaseDataSet.ColumnParameters thirtyTo34F = new eHospitalBaseDataSet.ColumnParameters("thirtyTo34F",
		        "30 - 34 female", "gender=F|age=30-34", "F8");
		eHospitalBaseDataSet.ColumnParameters thirty5To39F = new eHospitalBaseDataSet.ColumnParameters("thirty5To39F",
		        "35 - 39 female", "gender=F|age=35-39", "F9");
		eHospitalBaseDataSet.ColumnParameters fortyTo44F = new eHospitalBaseDataSet.ColumnParameters("fortyTo44F", "40 - 44 female",
		        "gender=F|age=40-44", "F10");
		eHospitalBaseDataSet.ColumnParameters forty5To49F = new eHospitalBaseDataSet.ColumnParameters("forty5To49F",
		        "45 - 49 female", "gender=F|age=45-49", "F11");
		// 50-54, 55-59, 60-64, 65+ female
		eHospitalBaseDataSet.ColumnParameters fiftyTo54F = new eHospitalBaseDataSet.ColumnParameters("fiftyTo54F", "50 - 54 female",
		        "gender=F|age=50-54", "F12");
		eHospitalBaseDataSet.ColumnParameters fifty5To59F = new eHospitalBaseDataSet.ColumnParameters("fifty5To59F",
		        "55 - 59 female", "gender=F|age=55-59", "F13");
		eHospitalBaseDataSet.ColumnParameters sixtyTo64F = new eHospitalBaseDataSet.ColumnParameters("sixtyTo64F", "60 - 64 female",
		        "gender=F|age=60-64", "F14");
		eHospitalBaseDataSet.ColumnParameters above65F = new eHospitalBaseDataSet.ColumnParameters("above65F", "65+ female",
		        "gender=F|age=65+", "F15");
		eHospitalBaseDataSet.ColumnParameters unknown = new eHospitalBaseDataSet.ColumnParameters("unknown", "Unknown age",
		        "age=UK", "UNK");
		eHospitalBaseDataSet.ColumnParameters total = new eHospitalBaseDataSet.ColumnParameters("total", "All total", "", "TALL");
		
		return Arrays.asList(unknownM, under1M, oneTo4M, fiveTo9M, tenTo14M, fifteenTo19M, twentyTo24M, twenty5To29M,
		    thirtyTo34M, thirty5To39M, fortyTo44M, forty5To49M, fiftyTo54M, fifty5To59M, sixtyTo64M, above65M, unknownF,
		    under1F, oneTo4F, fiveTo9F, tenTo14F, fifteenTo19F, twentyTo24F, twenty5To29F, thirtyTo34F, thirty5To39F,
		    fortyTo44F, forty5To49F, fiftyTo54F, fifty5To59F, sixtyTo64F, above65F, unknown, total);
	}
	
	public static List<eHospitalBaseDataSet.ColumnParameters> getDispensationColumnsGenderAndAge() {
		eHospitalBaseDataSet.ColumnParameters under15M = new eHospitalBaseDataSet.ColumnParameters("under15M",
		        "under 15 years male", "gender=M|age=<15", "01");
		
		eHospitalBaseDataSet.ColumnParameters plus15M = new eHospitalBaseDataSet.ColumnParameters("plus15M",
		        "more than 15 years male", "gender=M|age=15+", "02");
		
		eHospitalBaseDataSet.ColumnParameters unkM = new eHospitalBaseDataSet.ColumnParameters("unkM3", "unknown age male",
		        "gender=M|age=UK", "03");
		
		eHospitalBaseDataSet.ColumnParameters under15F = new eHospitalBaseDataSet.ColumnParameters("under15F",
		        "under 15 years female", "gender=F|age=<15", "04");
		eHospitalBaseDataSet.ColumnParameters plus15F = new eHospitalBaseDataSet.ColumnParameters("plus15F",
		        "more than 15 years female", "gender=F|age=15+", "05");
		
		eHospitalBaseDataSet.ColumnParameters unkF = new eHospitalBaseDataSet.ColumnParameters("unkF3", "unknown age female",
		        "gender=F|age=UK", "06");
		
		eHospitalBaseDataSet.ColumnParameters all3 = new eHospitalBaseDataSet.ColumnParameters("all3", "All dispensation", "", "07");
		
		return Arrays.asList(under15M, plus15M, unkM, under15F, plus15F, unkF, all3);
	}
	
}
