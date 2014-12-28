
public class DefaultVersions 
{
	//prefixes are as follows
	// release
	// old_beta
	// old_alpha
	
	///It's also worth noting that these will be the folder name of an un-modded profile (based on versions only)
	public static String[] defaultVersions = new String[]
	{
		//Release
		"1.8.1", "1.8", "1.7.10", "1.7.9", "1.7.5", "1.7.4", "1.7.2", "1.6.4", "1.6.2", "1.6.1", "1.5.1", "1.5.1", "1.4.7", "1.4.6", "1.4.5", "1.4.4",
		"1.4.2", "1.3.2", "1.3.1", "1.2.5", "1.2.4", "1.2.3", "1.2.2",
		"1.2.1", "1.1", "1.0.1", "1.0.0",
		
		//old_beta
		"b1.0", "b1.0_01", "b1.0.2",
		"b1.1", "b1.1_01", "b1.1_02",
		"b1.2", "b1.2_01", "b1.2_02",
		"b1.3", "b1.3_01",
		"b1.4", "b1.4_01",
		"b1.5", "b1.5_01",
		"b1.6", "b1.6.1", "b1.6.2", "b1.6.3", "b1.6.4", "b1.6.5", "b1.6.6",
		"b1.7", "b1.7.1", "b1.7.2", "b1.7.3",
		"b1.8", "b1.8.1",
		
		//old_alpha
		"a1.0.0", "a1.0.1", "a1.0.1_01", "a1.0.2_01", "a1.0.3", "a1.0.4", "a1.0.5", "a1.0.5_01", "a1.0.6", "a1.0.6", "a1.0.6_01", "a1.0.6_03", "a1.0.7", "a1.0.8", "a1.0.8_01", "a1.0.9", "a1.0.10", "a1.0.10", "a1.0.11", "a1.0.12", "a1.0.13", "a1.0.13_01", "a1.0.13_02", "a1.0.14", "a1.0.14_01", "a1.0.15", "a1.0.16", "a1.0.16_01", "a1.0.16_02", "a1.0.17", "1.0.17_03", "1.0.17_04",
		"a1.1.0", "a1.1.1", "a1.1.2", "a1.1.2_01",
		"a1.2.0", "a1.2.0_01", "a1.2.0_02", "a1.2.1", "a1.2.1_01", "a1.2.2", "a1.2.3", "a1.2.3_01", "a1.2.3_02", "a1.2.3_04", "a1.2.4", "a1.2.4_01", "a1.2.5", "a1.2.6"
	};
	public static String[] oldAlphaList()
	{
		return new String[]
		{
				"a1.0.0", "a1.0.1", "a1.0.1_01", "a1.0.2_01", "a1.0.3", "a1.0.4", "a1.0.5", "a1.0.5_01", "a1.0.6", "a1.0.6", "a1.0.6_01", "a1.0.6_03", "a1.0.7", "a1.0.8", "a1.0.8_01", "a1.0.9", "a1.0.10", "a1.0.10", "a1.0.11", "a1.0.12", "a1.0.13", "a1.0.13_01", "a1.0.13_02", "a1.0.14", "a1.0.14_01", "a1.0.15", "a1.0.16", "a1.0.16_01", "a1.0.16_02", "a1.0.17", "1.0.17_03", "1.0.17_04",
				"a1.1.0", "a1.1.1", "a1.1.2", "a1.1.2_01",
				"a1.2.0", "a1.2.0_01", "a1.2.0_02", "a1.2.1", "a1.2.1_01", "a1.2.2", "a1.2.3", "a1.2.3_01", "a1.2.3_02", "a1.2.3_04", "a1.2.4", "a1.2.4_01", "a1.2.5", "a1.2.6"
		};
	}
	public static String[] oldBetaList()
	{
		return new String[]
			{
				"b1.0", "b1.0_01", "b1.0.2",
				"b1.1", "b1.1_01", "b1.1_02",
				"b1.2", "b1.2_01", "b1.2_02",
				"b1.3", "b1.3_01",
				"b1.4", "b1.4_01",
				"b1.5", "b1.5_01",
				"b1.6", "b1.6.1", "b1.6.2", "b1.6.3", "b1.6.4", "b1.6.5", "b1.6.6",
				"b1.7", "b1.7.1", "b1.7.2", "b1.7.3",
				"b1.8", "b1.8.1"
			};
	}
	public static String[] curReleaseList()
	{
		return new String[]
			{
				"1.8.1", "1.8", "1.7.10", "1.7.9", "1.7.5", "1.7.4", "1.7.2", "1.6.4", "1.6.2", "1.6.1", "1.5.1", "1.5.1", "1.4.7", "1.4.6", "1.4.5", "1.4.4",
				"1.4.2", "1.3.2", "1.3.1", "1.2.5", "1.2.4", "1.2.3", "1.2.2",
				"1.2.1", "1.1", "1.0.1", "1.0.0"
			};
	}
}
