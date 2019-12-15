package io.phdata.examples

import java.io.{File, FileWriter}
import java.util.UUID

object NasaData {
  private val tmpDataFileName: String = UUID.randomUUID.toString
  private val dataFile = new File(tmpDataFileName)

  private val dodData =
    """Agency,Center,Center Search Status,Facility,Occupied,Status,URL Link,Record Date,Last Update,Address,City,State,ZIP,Country,Contact,Mail Stop,Phone
      |1,DOD,Air Force Research Laboratory,Public,"Mach 6, High Reynolds Number Facility",1960,Under Rehabilitation,,3/1/1996,9/9/2013,1864 4th St,Wright-Patterson AFB,OH,45433-7541,US,Mr. Chris Love,B015,937 255-1689
      |2,DOD,Air Force Research Laboratory,Public,Subsonic Aerodynamic Research Laboratory,1985,Active,,3/1/1996,9/9/2013,1864 4th St,Wright-Patterson AFB,OH,45433-7541,US,Mr. Chris Love,B015,937 255-1689
      |3,DOD,Air Force Research Laboratory,Public,Trisonic Gasdynamics Facility,1960,Active,,3/1/1996,9/9/2013,1864 4th St,Wright-Patterson AFB,OH,45433-7541,US,Mr. Chris Love,B015,937 255-1689
      |4,DOD,Air Force Research Laboratory,Public,Vertical Wind Tunnel,1960,Active,,3/1/1996,9/9/2013,1864 4th St,Wright-Patterson AFB,OH,45433-7541,US,Mr. Chris Love,B015,937 255-1689
      |5,DOD,Arnold Engineering Development Center,Public,10V Test Chamber,1965,Active,http://www.arnold.af.mil/library/factsheets/factsheet.asp?id=13106,3/1/1996,8/23/2013,"100 Kindel Dr, MS-1214",Arnold AFB,TN,37389-1214,US,Capabilities Integration Division,AEDC/XPR,(931) 454-6513
      |6,DOD,Arnold Engineering Development Center,Public,7V Sensor Test Facility,1994,Active,http://www.arnold.af.mil/library/factsheets/factsheet.asp?id=12978,3/1/1996,9/19/2012,"100 Kindel Dr, MS-1214",Arnold AFB,TN,37389-1214,US,Capabilities Integration Division,AEDC/XPR,(931) 454-6513
      |7,DOD,Arnold Engineering Development Center,Public,Mark I Aerospace Chamber,1963,Inactive,http://www.arnold.af.mil/library/factsheets/factsheet.asp?id=13114,3/1/1996,9/19/2012,"100 Kindel Dr, MS-1214",Arnold AFB,TN,37389-1214,US,Capabilities Integration Division,AEDC/XPR,(931) 454-6513
      |8,DOE,Pacific Northwest National Laboratory,Public,Atmospheric Measurements Laboratory,,Active,http://www.pnnl.gov/atmospheric/programs/atmos_measurement_lab.stm,9/23/2013,9/23/2013,Battelle Boulevard,Richland,WA,99352,US,Mardell Sours,LSB MSN J2-33,509-372-6026
      |9,DOE,Pacific Northwest National Laboratory,Public,Atmospheric Radiation Measurement Climate Research Facility,,Active,http://www.pnnl.gov/atmospheric/programs/arm.stm,9/23/2013,9/23/2013,Battelle Boulevard,Richland,WA,99352,US,Mardell Sours,LSB MSN J2-33,509-372-6026
      |10,DOE,Pacific Northwest National Laboratory,Public,Atmospheric Research Measurement Aerial Facility,,Active,http://www.pnnl.gov/atmospheric/programs/arm_aerial_vehicle_prog.stm,9/23/2013,9/23/2013,Battelle Boulevard,Richland,WA,99352,US,Mardell Sours,LSB MSN J2-33,509-372-6026
      |11,DOE,Pacific Northwest National Laboratory,Public,Biological Sciences Facility,,Active,www.pnnl.gov,9/23/2013,9/23/2013,Battelle Boulevard,Richland,WA,99352,US,Mardell Sours,LSB MSN J2-33,509-372-6026
      |12,DOE,Pacific Northwest National Laboratory,Public,Bioproducts Sciences and Engineering Laboratory,,Active,http://www.pnnl.gov/biobased/bsel.stm,9/23/2013,9/23/2013,Battelle Boulevard,Richland,WA,99352,US,Mardell Sours,LSB MSN J2-33,509-372-6026
      |13,DOE,Pacific Northwest National Laboratory,Public,Environmental and Molecular Sciences Laboratory,,Active,http://www.emsl.pnnl.gov/emslweb/,9/23/2013,9/23/2013,Battelle Boulevard,Richland,WA,99352,US,Mardell Sours,LSB MSN J2-33,509-372-6026
      |14,DOE,Pacific Northwest National Laboratory,Public,Joint Global Change Research Institute,,Active,http://www.pnnl.gov/atmospheric/programs/jgcri.stm,9/23/2013,9/23/2013,Battelle Boulevard,Richland,WA,99352,US,Mardell Sours,LSB MSN J2-33,509-372-6026
      |15,DOE,Pacific Northwest National Laboratory,Public,Marine Sciences Facility,,Active,http://marine.pnnl.gov/,9/23/2013,9/23/2013,Battelle Boulevard,Richland,WA,99352,US,Mardell Sours,LSB MSN J2-33,509-372-6026
      |16,DOE,Pacific Northwest National Laboratory,Public,Materials Sciences & Techonolgy Laboratory,,Active,www.pnnl.gov,9/23/2013,9/23/2013,Battelle Boulevard,Richland,WA,99352,US,Mardell Sours,LSB MSN J2-33,509-372-6026
      |17,DOE,Pacific Northwest National Laboratory,Public,Radiochemical Process Laboratory,,Active,http://rpl.pnnl.gov/,9/23/2013,9/23/2013,Battelle Boulevard,Richland,WA,99352,US,Mardell Sours,LSB MSN J2-33,509-372-6026
      |18,DOE,Pacific Northwest National Laboratory,Public,Research Aircraft Facility,,Active,http://www.pnnl.gov/atmospheric/programs/raf.stm,9/23/2013,9/23/2013,Battelle Boulevard,Richland,WA,99352,US,Mardell Sours,LSB MSN J2-33,509-372-6026
      |19,DOE,Pacific Northwest National Laboratory,Public,Ultra-low Background Counting Laboratory,,Active,www.pnnl.gov,9/23/2013,9/23/2013,Battelle Boulevard,Richland,WA,99352,US,Mardell Sours,LSB MSN J2-33,509-372-6026
      |""".stripMargin
  private val intelsatData =
    """Intelsat,Intelsat General,Public,Environmental Test Lab-Shaker,1969,Active,,4/29/1996,8/26/2013,28035 Ambergate Drive,Rancho Palos Verdes,CA,90275,US,Bryan Benedict,In-Orbit Servicing,424 206-2725
      |21,Intelsat,Intelsat General,Public,Environmental Test Lab-Test Chamber ,1969,,,4/29/1996,8/22/2013,28035 Ambergate Drive,Rancho Palos Verdes,CA,90275,US,Bryan Benedict,In-Orbit Servicing,424 206-2725
      |22,Intelsat,Intelsat General,Public,Large Anechoic Chamber ,1969,,,4/29/1996,8/22/2013,28035 Ambergate Drive,Rancho Palos Verdes,CA,90275,US,Bryan Benedict,In-Orbit Servicing,424 206-2725
      |23,Intelsat,Intelsat General,Public,Outdoor Antenna Test Range ,1969,,,4/29/1996,8/22/2013,28035 Ambergate Drive,Rancho Palos Verdes,CA,90275,US,Bryan Benedict,In-Orbit Servicing,424 206-2725
      |24,Intelsat,Intelsat General,Public,Scanning Auger Multiprobe; Electron Microscope ,1969,,,4/29/1996,8/22/2013,28035 Ambergate Drive,Rancho Palos Verdes,CA,90275,US,Bryan Benedict,In-Orbit Servicing,424 206-2725
      |25,Intelsat,Intelsat General,Public,Semiconductor Fabrication 1 ,1969,,,4/29/1996,8/22/2013,28035 Ambergate Drive,Rancho Palos Verdes,CA,90275,US,Bryan Benedict,In-Orbit Servicing,424 206-2725
      |26,Intelsat,Intelsat General,Public,Semiconductor Fabrication 2 ,1969,,,4/29/1996,8/22/2013,28035 Ambergate Drive,Rancho Palos Verdes,CA,90275,US,Bryan Benedict,In-Orbit Servicing,424 206-2725
      |27,Intelsat,Intelsat General,Public,Semiconductor Fabrication 3 ,1969,,,4/29/1996,8/22/2013,28035 Ambergate Drive,Rancho Palos Verdes,CA,90275,US,Bryan Benedict,In-Orbit Servicing,424 206-2725
      |28,Intelsat,Intelsat General,Public,Semiconductor Fabrication 4 ,1969,,,4/29/1996,8/22/2013,28035 Ambergate Drive,Rancho Palos Verdes,CA,90275,US,Bryan Benedict,In-Orbit Servicing,424 206-2725
      |29,Intelsat,Intelsat General,Public,Semiconductor Fabrication 5 ,1969,,,4/29/1996,8/22/2013,28035 Ambergate Drive,Rancho Palos Verdes,CA,90275,US,Bryan Benedict,In-Orbit Servicing,424 206-2725
      |30,Intelsat,Intelsat General,Public,Semiconductor Fabrication 6 ,1969,,,4/29/1996,8/22/2013,28035 Ambergate Drive,Rancho Palos Verdes,CA,90275,US,Bryan Benedict,In-Orbit Servicing,424 206-2725
      |31,Intelsat,Intelsat General,Public,Semiconductor Fabrication 7 ,1969,,,4/29/1996,8/22/2013,28035 Ambergate Drive,Rancho Palos Verdes,CA,90275,US,Bryan Benedict,In-Orbit Servicing,424 206-2725
      |32,Intelsat,Intelsat General,Public,Small Anechoic Chamber ,1969,,,4/29/1996,8/22/2013,28035 Ambergate Drive,Rancho Palos Verdes,CA,90275,US,Bryan Benedict,In-Orbit Servicing,424 206-2725
      |33,Intelsat,Intelsat General,Public,SSTD ,1969,,,4/29/1996,8/22/2013,28035 Ambergate Drive,Rancho Palos Verdes,CA,90275,US,Bryan Benedict,In-Orbit Servicing,424 206-2725
      |34,Intelsat,Intelsat General,Public,Subjective Evaluation Room ,1969,,,4/29/1996,8/22/2013,28035 Ambergate Drive,Rancho Palos Verdes,CA,90275,US,Bryan Benedict,In-Orbit Servicing,424 206-2725
      |35,Intelsat,Intelsat General,Public,System Simulation Laboratory ,1969,,,4/29/1996,8/22/2013,28035 Ambergate Drive,Rancho Palos Verdes,CA,90275,US,Bryan Benedict,In-Orbit Servicing,424 206-2725
      |36,Intelsat,Intelsat General,Public,Video System Lab ,1969,,,4/29/1996,8/22/2013,28035 Ambergate Drive,Rancho Palos Verdes,CA,90275,US,Bryan Benedict,In-Orbit Servicing,424 206-2725
    """.stripMargin
  private val nasaAtoGData =
    """37,NASA 2,Ames Research Center,Public,046 - AIRCRAFT MAINTENANCE HANGAR 2,1943,Active,,10/5/2011,9/10/2014,Code RC,Moffett Field,CA,94035,US,Rocci Caringello,213-1,650 603-9506
      |38,NASA 2,Ames Research Center,Public,DYNAVAC THERMAL VACUUM CHAMBER,2010,Active,http://www.nasa.gov/centers/ames/research/technology-onepagers/eng_eval_lab.html,10/29/2014,10/29/2014,Code RC,Moffett Field,CA,94035,US,Rocci Caringello,213-1,650 603-9506
      |39,NASA 2,Ames Research Center,Public,N204A - SPACE TECHNOLOGY BUILDING: AMES VERTICAL GUN RANGE (PAPAC),1966,Active,http://thermo-physics.arc.nasa.gov,3/1/1996,6/12/2014,Code RC,Moffett Field,CA,94035,US,Rocci Caringello,213-1,650 603-9506
      |40,NASA 2,Ames Research Center,Public,N206A - 12 FOOT PRESSURE WIND TUNNEL AUXILIARIES (PAPAC),1946,Active,,3/1/1996,11/22/2013,Code RC,Moffett Field,CA,94035,US,Rocci Caringello,213-1,650 603-9506
      |41,NASA 2,Ames Research Center,Public,N221A - 20-G AND HUMAN POWER CENTRIFUGE (PAPAC),1964,Active,http://spacebiosciences.arc.nasa.gov/page/20-g-centrifuge,3/1/1996,1/18/2012,Code RC,Moffett Field,CA,94035,US,Rocci Caringello,213-1,650 603-9506
      |42,NASA 2,Ames Research Center,Public,N229 - EXPER. AEROTHERMODYNAMIC FAC.: ELECTRIC ARC SHOCK TUBE FACILITY (PAPAC),1961,Active,http://thermo-physics.arc.nasa.gov,3/1/1996,6/13/2014,Code RC,Moffett Field,CA,94035,US,Rocci Caringello,213-1,650 603-9506
      |43,NASA 2,Ames Research Center,Public,N234 - THERMAL PROTECTION LAB: 2x9 INCH SUPERSONIC TURBULENT FLOW DUCT (PAPAC),1972,Active,http://thermo-physics.arc.nasa.gov,3/1/1996,6/12/2014,Code RC,Moffett Field,CA,94035,US,Rocci Caringello,213-1,650 603-9506
      |44,NASA 2,Ames Research Center,Public,N234 - THERMAL PROTECTION LAB: AERODYNAMIC HEATING FACILITY (PAPAC),1962,Active,http://thermo-physics.arc.nasa.gov,3/1/1996,6/13/2014,Code RC,Moffett Field,CA,94035,US,Rocci Caringello,213-1,650 603-9506
      |45,NASA 2,Ames Research Center,Public,N234A - THERMAL PROTECTION LAB BOILER: STEAM VACUUM SYSTEM (PAPAC),1962,Active,http://thermo-physics.arc.nasa.gov,3/1/1996,6/12/2014,Code RC,Moffett Field,CA,94035,US,Rocci Caringello,213-1,650 603-9506
      |46,NASA 2,Ames Research Center,Public,N237 - HYPERVELOCITY FREE FLIGHT AERODYNAMIC FACILITY (PAPAC),1964,Active,http://thermo-physics.arc.nasa.gov,3/1/1996,6/13/2014,Code RC,Moffett Field,CA,94035,US,Rocci Caringello,213-1,650 603-9506
      |47,NASA 2,Ames Research Center,Public,N237 - HYPERVELOCITY FREE FLIGHT GUN DEVELOPMENT FACILITY (PAPAC),1964,Inactive,http://thermo-physics.arc.nasa.gov,3/1/1996,7/19/2013,Code RC,Moffett Field,CA,94035,US,Rocci Caringello,213-1,650 603-9506
      |48,NASA 2,Ames Research Center,Public,N238 - ARC JET LABORATORY: INTERACTION HEATING FACILITY (PAPAC),1975,Active,http://thermo-physics.arc.nasa.gov,3/1/1996,6/13/2014,Code RC,Moffett Field,CA,94035,US,Rocci Caringello,213-1,650 603-9506
      |49,NASA 2,Ames Research Center,Public,N238 - ARC JET LABORATORY: PANEL TEST FACILITY (PAPAC),1972,Active,http://thermo-physics.arc.nasa.gov,3/1/1996,6/12/2014,Code RC,Moffett Field,CA,94035,US,Rocci Caringello,213-1,650 603-9506
      |50,NASA 2,Ames Research Center,Public,N240 - CENTER FOR ENGINEERING INNOVATION: FLIGHT PROCESSING CENTER,1982,Active,,3/1/1996,1/30/2012,Code RC,Moffett Field,CA,94035,US,Rocci Caringello,213-1,650 603-9506
      |51,NASA 2,Ames Research Center,Public,N240 & N244 - MULTI-MISSION OPERATIONS CENTER,,Active,,1/27/2012,1/30/2012,Code RC,Moffett Field,CA,94035,US,Rocci Caringello,213-1,650 603-9506
      |52,NASA 2,Armstrong Flight Research Center,Public,Bldg. 0703 - Dryden Aircraft Operations Facility,,Active,www.nasa.gov/centers/daof/index.html,4/13/2010,12/17/2014,P.O.Box 273,Edwards,CA,93523-0273,US,Facilities Utilization Officer,"B-4800, Room 2004",661-276-2585
      |53,NASA 2,Armstrong Flight Research Center,Public,Bldg. 4800 - Research Development & Test Facility,1954,Active,www.nasa.gov/centers/dryden/home/index.html,4/13/2010,12/19/2014,P.O.Box 273,Edwards,CA,93523-0273,US,Facilities Utilization Officer,"B-4800, Room 2004",661-276-2585
      |54,NASA 2,Armstrong Flight Research Center,Public,Bldg. 4820 - Flight Loads Laboratory ,1966,Active,www.nasa.gov/centers/dryden/research/Facilities/FL,3/1/1996,7/10/2013,P.O.Box 273,Edwards,CA,93523-0273,US,Facilities Utilization Officer,"B-4800, Room 2004",661-276-2585
      |55,NASA 2,Armstrong Flight Research Center,Public,Bldg. 4824 - Communications Building,,Active,,4/13/2010,12/19/2014,P.O.Box 273,Edwards,CA,93523-0273,US,Facilities Utilization Officer,"B-4800, Room 2004",661-276-2585
      |56,NASA 2,Armstrong Flight Research Center,Public,Bldg. 4840 - Research Aircraft Integration Facility,1992,Active,www.nasa.gov/centers/dryden/pdf/152685main_RAIF_fa,3/1/1996,12/17/2014,P.O.Box 273,Edwards,CA,93523-0273,US,Facilities Utilization Officer,"B-4800, Room 2004",661-276-2585
      |57,NASA 2,Armstrong Flight Research Center,Public,Bldg. 4982 - Aeronautical Tracking Facility,,Active,,4/13/2010,12/19/2014,P.O.Box 273,Edwards,CA,93523-0273,US,Facilities Utilization Officer,"B-4800, Room 2004",661-276-2585
      |58,NASA 2,Armstrong Flight Research Center,Public,Bldg. 703 Mirror Coating Facility,,Active,,3/25/2013,7/10/2013,P.O.Box 273,Edwards,CA,93523-0273,US,Facilities Utilization Officer,"B-4800, Room 2004",661-276-2585
      |59,NASA 2,Glenn Research Center,Public,"2.2 Second Drop Tower, Bldg. 45",1958,Active,http://microgravity.grc.nasa.gov/drop2/,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |60,NASA 2,Glenn Research Center,Public,"8 x 6 Supersonic Wind Tunnel, Bldgs. 39, 53 & 54",1948,Active,http://facilities.grc.nasa.gov/8x6/index.html,3/1/1996,8/6/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |61,NASA 2,Glenn Research Center,Public,"9 X 15 Low Speed Wind Tunnel, Bldg. 39",1969,Active,http://facilities.grc.nasa.gov/9x15/index.html,3/1/1996,8/6/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |62,NASA 2,Glenn Research Center,Public,"Aero Acoustic Propulsion Lab., (AAPL) Bldg. 145",1990,Active,http://facilities.grc.nasa.gov/aapl/index.html,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |63,NASA 2,Glenn Research Center,Public,Aerodynamics Flow Physics Facilities (ERB Complex),1942,Active,http://facilities.grc.nasa.gov/erb/index.html,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |64,NASA 2,Glenn Research Center,Public,Aeropropulsion Heat Transfer Technology Facilities,1942,Active,http://facilities.grc.nasa.gov/erb/index.html,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |65,NASA 2,Glenn Research Center,Public,Combustion and Aerochemistry Technology Facilities,1942,Active,http://facilities.grc.nasa.gov/erb/index.html,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |66,NASA 2,Glenn Research Center,Public,Complex of Thirteen Space Test Chambers (Bldg.16),1942,Active,http://facilities.grc.nasa.gov/eprb/index.html,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |67,NASA 2,Glenn Research Center,Public,"Cryogenic Propellant Tank Fac. (K-Site), #2811",1965,Active,http://www.grc.nasa.gov/WWW/Facilities/ext/ksite/index.html,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |68,NASA 2,Glenn Research Center,Public,DeHavilland DHC-6 Aircraft (N607NA),1942,Active,http://www.lerc.nasa.gov/WWW/AFED/facilities/flig,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |69,NASA 2,Glenn Research Center,Public,Electric Power Lab - Vacuum Facility 5 (VF-5),1961,Active,http://www.grc.nasa.gov/WWW/Facilities/ext/capabilities/spacesim_lewis.html,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |70,NASA 2,Glenn Research Center,Public,Electric Power Lab - Vacuum Facility 6 (VF-6),1961,Active,http://facilities.grc.nasa.gov/epl/index.html,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |71,NASA 2,Glenn Research Center,Public,Electro Magnetic Interference (EMI) Lab,1942,Active,http://facilities.grc.nasa.gov/emi/index.html,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |72,NASA 2,Glenn Research Center,Public,"Icing Research Tunnel (IRT), Bldg #11",1943,Active,http://www.grc.nasa.gov/WWW/Facilities/ext/irt/ind,3/1/1996,8/7/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |73,NASA 2,Glenn Research Center,Public,Learjet 25 Aircraft (N616NA),1942,Active,http://www.lerc.nasa.gov/WWW/AFED/facilities/flig,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |74,NASA 2,Glenn Research Center,Public,"Materials & Structures Complex (49, 105, 106,etc.)",1943,Active,,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |75,NASA 2,Glenn Research Center,Public,Plumbrook Engineering Building,1966,Active,,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |76,NASA 2,Glenn Research Center,Public,"Power Systems Facility, Bldg. 333 & 333A",1989,Active,http://www.grc.nasa.gov/WWW/Facilities/ext/psf/ind,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |77,NASA 2,Glenn Research Center,Public,"Propulsion Systems Laboratory, Bldg. 125",1972,Active,http://facilities.grc.nasa.gov/psl/,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |78,NASA 2,Glenn Research Center,Public,"Research Analysis Center, Bldg. 142",1980,Active,,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |79,NASA 2,Glenn Research Center,Public,"Research Combustion Laboratory, Bldg. 35",1945,Active,http://www.grc.nasa.gov/WWW/Facilities/ext/rcl/ind,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |80,NASA 2,Glenn Research Center,Public,Reverberant Acoustic Test Facility (RATF),,Under Construction,http://facilities.grc.nasa.gov/spf/,7/8/2010,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |81,NASA 2,Glenn Research Center,Public,"Space Experiments Laboratory, Bldg. 110",1993,Active,,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |82,NASA 2,Glenn Research Center,Public,"Statics Test Laboratory, Bldg. 5 (ERB Complex)",1942,Active,http://www.grc.nasa.gov/WWW/Facilities/ext/erb/ind,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |83,NASA 2,Glenn Research Center,Public,"Structural Dynamics Laboratory, Bldg. 56",1949,Active,http://facilities.grc.nasa.gov/sdl/index.html,3/1/1996,1/5/2012,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |84,NASA 2,Glenn Research Center,Public,Turbomachinery Technology Facilities (ERB Complex),1942,Active,http://www.grc.nasa.gov/WWW/Facilities/ext/erb/ind,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |85,NASA 2,Glenn Research Center,Public,Vacuum Facility 11 (VF-11) Bldg. 16,,Active,http://facilities.grc.nasa.gov/epl/index.html,7/15/2010,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |86,NASA 2,Glenn Research Center,Public,Vacuum Facility 12 (VF-12) Bldg. 301,,Active,http://facilities.grc.nasa.gov/epl/index.html,7/15/2010,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |87,NASA 2,Glenn Research Center,Public,Vacuum Facility 16 (VF-16) Bldg. 16,,Active,http://facilities.grc.nasa.gov/epl/index.html,7/15/2010,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |88,NASA 2,Glenn Research Center,Public,Vacuum Facility 7 (VF-7) Bldg. 16,,Active,http://facilities.grc.nasa.gov/epl/index.html,7/15/2010,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |89,NASA 2,Glenn Research Center,Public,"Zero Gravity Research Facility, (Bldg. 110)",1993,Active,http://microgravity.grc.nasa.gov/zero-g/,3/1/1996,8/13/2013,21000 Brookpark Road,Cleveland,OH,44135,US,Linda C. Elonen-Wright,8-Jun,216-433-9370
      |90,NASA 2,Goddard Space Flight Center,Public,"500 BDA: Antenna, 9m S-band",,Active,,3/1/1996,8/15/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |91,NASA 2,Goddard Space Flight Center,Public,500 BDA: Bermuda Tracking Station (BDA),,Active,,3/1/1996,8/15/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |92,NASA 2,Goddard Space Flight Center,Public,"500 BDA: Radar, FPQ-6",,Active,,3/1/1996,8/15/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |93,NASA 2,Goddard Space Flight Center,Public,500 Flight Dynamics Facility,1961,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |94,NASA 2,Goddard Space Flight Center,Public,500 MILA : Merritt Island Tracking Station (MILA),1966,Active,,3/1/1996,8/15/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |95,NASA 2,Goddard Space Flight Center,Public,"500 MILA: Antenna, 4.3m S-band",1966,Active,,3/1/1996,8/15/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |96,NASA 2,Goddard Space Flight Center,Public,"500 MILA: Antenna, 9m S-band",1966,Active,,3/1/1996,8/15/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |97,NASA 2,Goddard Space Flight Center,Public,"500 WSC: Antenna, 10m S-band",1977,Active,,3/1/1996,8/15/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |98,NASA 2,Goddard Space Flight Center,Public,"500 WSC: Antenna, 19m S/Ku-band",1977,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |99,NASA 2,Goddard Space Flight Center,Public,"500/WSC: Antenna, 4.5m S/Ku-band",1977,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |100,NASA 2,Goddard Space Flight Center,Public,500/WSC: Data Interface System,1977,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |101,NASA 2,Goddard Space Flight Center,Public,500: Mission Operations Center Facility,1961,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |102,NASA 2,Goddard Space Flight Center,Public,"500: NASA Communications Facility (NASCOM), B. 3",1961,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |103,NASA 2,Goddard Space Flight Center,Public,"500: NASA Communications Facility (NASCOM), B.14",1964,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |104,NASA 2,Goddard Space Flight Center,Public,500: Space-Ground Link Terminal,1977,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |105,NASA 2,Goddard Space Flight Center,Public,600: Plasma Calibration Facility,1960,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |106,NASA 2,Goddard Space Flight Center,Public,700: Cryogenic Research and Integration Facility,1994,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |107,NASA 2,Goddard Space Flight Center,Public,700: Environmental Test Engineering & Integration,1962,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |108,NASA 2,Goddard Space Flight Center,Public,700: Global Positioning System (GPS) Verification,1963,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |109,NASA 2,Goddard Space Flight Center,Public,700: High Capacity Centrifuge (HCC),1965,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |110,NASA 2,Goddard Space Flight Center,Public,700: Magnetic Field Component Test Facility,1963,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |111,NASA 2,Goddard Space Flight Center,Public,"700: Space Environment Simulator, Facility 290",1962,Active,http://mscweb.gsfc.nasa.gov/549web,3/1/1996,8/19/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |112,NASA 2,Goddard Space Flight Center,Public,"700: Space Simulation Laboratory, Building 7/10/15",1962,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |113,NASA 2,Goddard Space Flight Center,Public,"700: SSDIF Clean Room, B.29",1990,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |114,NASA 2,Goddard Space Flight Center,Public,900: NASA Center for Computational Sciences/ B. 28,,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |115,NASA 2,Goddard Space Flight Center,Public,Acoustic Test Facility Building 10,,Active,http://mscweb.gsfc.nasa.gov/549web,7/8/2010,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |116,NASA 2,Goddard Space Flight Center,Public,"Facility 225, Thermal Vacuum Chamber, B.7",1962,Active,http://mscweb.gsfc.nasa.gov/549web,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |117,NASA 2,Goddard Space Flight Center,Public,"Facility 238, Thermal Vacuum Chamber, B.7",,Active,http://mscweb.gsfc.nasa.gov/549web,7/9/2010,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |118,NASA 2,Goddard Space Flight Center,Public,Sensor Data Processing Facility,1965,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |119,NASA 2,Goddard Space Flight Center,Public,TDRSS Control Center,1977,Active,,3/1/1996,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |120,NASA 2,Goddard Space Flight Center,Public,Vibration Test Facility - Exciter 1,,Active,http://mscweb.gsfc.nasa.gov/549web,7/7/2010,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
      |121,NASA 2,Goddard Space Flight Center,Public,Vibration Test Facility - Exciter 2,,Active,http://mscweb.gsfc.nasa.gov/549web,7/7/2010,8/16/2013,Greenbelt Road,Greenbelt,MD,20771,US,Barry Green,Code 500,301 286 2520
    """.stripMargin
  private val nasaJtoLData =
    """122,NASA 2,Jet Propulsion Lab,Public,10-Ft Vertical Space Simulator,1965,Inactive,http://etl.jpl.nasa.gov,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |123,NASA 2,Jet Propulsion Lab,Public,24 inch Telescope,1965,Active,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |124,NASA 2,Jet Propulsion Lab,Public,25-Ft Space Simulator,1961,Active,http://etl.jpl.nasa.gov,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |125,NASA 2,Jet Propulsion Lab,Public,48-inch Telescope,1988,Active,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |126,NASA 2,Jet Propulsion Lab,Public,Acoustics Noise Test Cell,1961,Active,http://etl.jpl.nasa.gov,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |127,NASA 2,Jet Propulsion Lab,Public,"Atomic Oxygen Test Facility, 121-100",1965,Active,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |128,NASA 2,Jet Propulsion Lab,Public,Computer Vision Lab.,1948,Active,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |129,NASA 2,Jet Propulsion Lab,Public,CTA-21,1955,Active,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |130,NASA 2,Jet Propulsion Lab,Public,DSS 12 Antenna,1958,Inactive,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |131,NASA 2,Jet Propulsion Lab,Public,DSS 13 Antenna,1958,Active,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |132,NASA 2,Jet Propulsion Lab,Public,DSS 14 Antenna,1958,Active,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |133,NASA 2,Jet Propulsion Lab,Public,DSS 15 Antenna,1958,Active,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |134,NASA 2,Jet Propulsion Lab,Public,DSS 16 Antenna,1958,Inactive,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |135,NASA 2,Jet Propulsion Lab,Public,DSS 24 Antenna,1994,Active,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |136,NASA 2,Jet Propulsion Lab,Public,DSS 43 Antenna,1963,Active,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |137,NASA 2,Jet Propulsion Lab,Public,DSS 45 Antenna,1963,Active,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |138,NASA 2,Jet Propulsion Lab,Public,DSS 46 Antenna,1963,Inactive,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |139,NASA 2,Jet Propulsion Lab,Public,DSS 61 Antenna,1964,Active,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |140,NASA 2,Jet Propulsion Lab,Public,DSS 63 Antenna,1964,Active,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |141,NASA 2,Jet Propulsion Lab,Public,DSS 65 Antenna,1964,Active,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |142,NASA 2,Jet Propulsion Lab,Public,DSS 66 Antenna,1964,Inactive,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |143,NASA 2,Jet Propulsion Lab,Public,Energy Conservation Laboratory High Bay Chamber,,Active,,7/6/2010,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |144,NASA 2,Jet Propulsion Lab,Public,Energy Conservation Laboratory Patio Chamber,,Active,,7/6/2010,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |145,NASA 2,Jet Propulsion Lab,Public,Frequency Standards Laboratory,1985,Active,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |146,NASA 2,Jet Propulsion Lab,Public,Large Shaker Dynamics Test Facility,1964,Active,http://etl.jpl.nasa.gov,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |147,NASA 2,Jet Propulsion Lab,Public,Lidar Facility,1973,Active,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |148,NASA 2,Jet Propulsion Lab,Public,Mesa Antenna Test Facility,1980,Active,,3/1/1996,8/7/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |149,NASA 2,Jet Propulsion Lab,Public,Microdevices Laboratory (MDL),1990,Active,,3/1/1996,8/8/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |150,NASA 2,Jet Propulsion Lab,Public,Mirror Refurbishment Building,1992,Active,,3/1/1996,8/8/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |151,NASA 2,Jet Propulsion Lab,Public,Optical Laboratories,1993,Active,,3/1/1996,8/8/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |152,NASA 2,Jet Propulsion Lab,Public,Remote Surface Lab,1972,Active,,3/1/1996,8/8/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |153,NASA 2,Jet Propulsion Lab,Public,Robotic Vehicle Lab,1948,Active,,3/1/1996,8/8/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |154,NASA 2,Jet Propulsion Lab,Public,SOLAR TEST FACILITY,1985,Active,,3/1/1996,8/8/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |155,NASA 2,Jet Propulsion Lab,Public,Space Flight Operations Facility,1964,Active,,3/1/1996,8/8/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |156,NASA 2,Jet Propulsion Lab,Public,Spacecraft Assembly Facility - High Bay 1 ,1962,Active,,3/1/1996,6/26/2012,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |157,NASA 2,Jet Propulsion Lab,Public,Spacecraft Assembly Facility - High Bay 2 ,1962,Active,,3/1/1996,6/26/2012,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |158,NASA 2,Jet Propulsion Lab,Public,Thermal Vacuum Chamber 144-TV-10,1964,Active,http://etl.jpl.nasa.gov,7/6/2010,8/8/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |159,NASA 2,Jet Propulsion Lab,Public,Thermal Vacuum Chamber 306-TV-11,,Active,http://etl.jpl.nasa.gov,7/6/2010,8/8/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |160,NASA 2,Jet Propulsion Lab,Public,Thermal-Vacuum Chamber 144-TV-7,1961,Active,http://etl.jpl.nasa.gov,3/1/1996,8/8/2013,JPL:2800,PASADENA,CA,91109-8099,US,Gary Gray,JPL:2800,818.354.0701
      |161,NASA 2,Johnson Space Center,Public,"11 ft. Chamber, Space Suit Dev. & Cert, EC, B-7",1989,Active,http://www.nasa.gov/centers/johnson/engineering/test_facilities/human_space_testing/11foot/index.html,3/1/1996,3/20/2012,2101 NASA Parkway,Houston,TX,77058,US,Charles Noel,JP,281.483.3219
      |162,NASA 2,Johnson Space Center,Public,"Chamber A, EC, B-32 ",1964,Under Rehabilitation,http://ctsdtests.jsc.nasa.gov/A.html,3/1/1996,9/26/2012,2101 NASA Parkway,Houston,TX,77058,US,Charles Noel,JP,281.483.3219
      |163,NASA 2,Johnson Space Center,Public,"Chamber B, EC, B-32 ",1964,Active,http://www.nasa.gov/centers/johnson/engineering/test_facilities/human_space_testing/detChamberB.html,3/1/1996,9/26/2012,2101 NASA Parkway,Houston,TX,77058,US,Charles Noel,JP,281.483.3219
      |164,NASA 2,Johnson Space Center,Public,"Dual Glove Thermal Vacuum Facility, EC, B-7",,Active,http://www.nasa.gov/centers/johnson/engineering/test_facilities/human_space_testing/glovebox/index.html,7/13/2010,3/20/2012,2101 NASA Parkway,Houston,TX,77058,US,Charles Noel,JP,281.483.3219
      |165,NASA 2,Johnson Space Center,Public,General Vibration Lab (GVL),2012,Active,http://www.nasa.gov/centers/johnson/pdf/639503main_VATF_User_Test_Planning_Guide.pdf,7/8/2010,9/26/2012,2101 NASA Parkway,Houston,TX,77058,US,Charles Noel,JP,281.483.3219
      |166,NASA 2,Johnson Space Center,Public,"Insitu Resource Utilization Test Fac, EP, B-353",1964,Active,https://innovation2011.jsc.nasa.gov/Exhibits/Details.cfm?ExhibitId=abce6d1a-30af-4fe8-a558-64905a009,3/1/1996,3/20/2012,2101 NASA Parkway,Houston,TX,77058,US,Charles Noel,JP,281.483.3219
      |167,NASA 2,Johnson Space Center,Public,"Sonic Fatique Lab (SFL), B-49",,Active,http://www.nasa.gov/centers/johnson/pdf/639503main_VATF_User_Test_Planning_Guide.pdf,7/8/2010,9/26/2012,2101 NASA Parkway,Houston,TX,77058,US,Charles Noel,JP,281.483.3219
      |168,NASA 2,Johnson Space Center,Public,"Spacecraft Acoustic Lab (SAL), B-49",,Active,http://www.nasa.gov/centers/johnson/pdf/639503main_VATF_User_Test_Planning_Guide.pdf,7/8/2010,9/26/2012,2101 NASA Parkway,Houston,TX,77058,US,Charles Noel,JP,281.483.3219
      |169,NASA 2,Johnson Space Center,Public,"SSATA Vacuum Chamber, EC, B-7",,Active,http://www.nasa.gov/centers/johnson/engineering/test_facilities/human_space_testing/iss_airlock/index.html,7/13/2010,3/20/2012,2101 NASA Parkway,Houston,TX,77058,US,Charles Noel,JP,281.483.3219
      |170,NASA 2,Johnson Space Center,Public,Vibration Acoustic Test Facility - B-49,,Active,http://www.nasa.gov/centers/johnson/pdf/639503main_VATF_User_Test_Planning_Guide.pdf,9/26/2012,9/26/2012,2101 NASA Parkway,Houston,TX,77058,US,Charles Noel,JP,281.483.3219
      |171,NASA 2,Langley Research Center,Public,16-Meter Thermal Vacuum Chamber,1963,Active,,3/1/1996,8/2/2012,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |172,NASA 2,Langley Research Center,Public,20-Inch Mach CF4 Tunnel,1960,Inactive,http://www.aeronautics.nasa.gov/atp/index.html,3/1/1996,1/28/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |173,NASA 2,Langley Research Center,Public,Aircraft Landing Dynamics Facility/B1262,1956,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |174,NASA 2,Langley Research Center,Public,Automated Structures Assembly Lab,1945,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |175,NASA 2,Langley Research Center,Public,Ceramic & Glass Laboratory (B-1237A),1978,Active,,3/1/1996,2/13/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |176,NASA 2,Langley Research Center,Public,Compact Range Pilot Facility,1965,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |177,NASA 2,Langley Research Center,Public,Composite Material Development Laboratory/B1238B,1978,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |178,NASA 2,Langley Research Center,Public,Diode Characterization & Test Lab (B-1202),,Active,,12/6/2005,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |179,NASA 2,Langley Research Center,Public,Electron Beam Welding Lab,1946,Active,,3/1/1996,2/15/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |180,NASA 2,Langley Research Center,Public,Experimental Avionics Systems Integration Lab,1951,Active,,3/1/1996,2/15/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |181,NASA 2,Langley Research Center,Public,Experimental Test Range,1990,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |182,NASA 2,Langley Research Center,Public,Fabrication Lab,1958,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |183,NASA 2,Langley Research Center,Public,Flight Research Facility,1951,Active,,3/1/1996,2/15/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |184,NASA 2,Langley Research Center,Public,"Flight Simulation Facilities, B1268A and B1268D",,Active,,7/6/2009,2/15/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |185,NASA 2,Langley Research Center,Public,Flight Systems Integration and Test Lab/B1250,1968,Active,,6/22/2006,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |186,NASA 2,Langley Research Center,Public,Helicopter Hover Facility/647,1977,Active,,3/1/1996,2/15/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |187,NASA 2,Langley Research Center,Public,High Heat Flux Facility,1940,Active,,3/1/1996,2/19/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |188,NASA 2,Langley Research Center,Public,Hypersonic Propulsion Facility,1946,Active,,3/1/1996,2/15/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |189,NASA 2,Langley Research Center,Public,Information Systems Research Facility (1),1945,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |190,NASA 2,Langley Research Center,Public,Information Systems Research Facility (2),1945,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |191,NASA 2,Langley Research Center,Public,Infrared Detector Calib & Charac Lab (B-1202),,Active,,12/6/2005,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |192,NASA 2,Langley Research Center,Public,Instrument Research Lab/1230,1946,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |193,NASA 2,Langley Research Center,Public,Intelligent Sys Res Lab/Hydrau Manipulator Testbed,1945,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |194,NASA 2,Langley Research Center,Public,Intravehicular Automation and Robotics Lab,1945,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |195,NASA 2,Langley Research Center,Public,Investment Casting Development Lab/B1237A,,Active,,6/15/2006,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |196,NASA 2,Langley Research Center,Public,Jet Noise Laboratory,1995,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |197,NASA 2,Langley Research Center,Public,Landing and Impact Research Facility,1966,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |198,NASA 2,Langley Research Center,Public,Low Frequency Antenna Test Facility,1965,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |199,NASA 2,Langley Research Center,Public,Low Pressure Physics Laboratory,1965,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |200,NASA 2,Langley Research Center,Public,Low Turbulence Pressure Tunnel,1940,Inactive,http://ad-www.larc.nasa.gov/facility/photo_gallery,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |201,NASA 2,Langley Research Center,Public,Materials Processing and Development Lab,1960,Active,,3/1/1996,2/19/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |202,NASA 2,Langley Research Center,Public,Metal Technology Development Lab/1232A,1946,Active,,6/9/2006,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |203,NASA 2,Langley Research Center,Public,Micometeroid/LDEF Analysis Laboratory,1965,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |204,NASA 2,Langley Research Center,Public,Microelectronics Development Lab (B-1238A),,Active,,12/6/2005,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |205,NASA 2,Langley Research Center,Public,National Transonic Facility,1982,Active,http://www.aeronautics.nasa.gov/atp/index.html,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |206,NASA 2,Langley Research Center,Public,Nondestructive Evaluation Lab,1988,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |207,NASA 2,Langley Research Center,Public,Nozzle Test Chamber,1952,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |208,NASA 2,Langley Research Center,Public,Productivity Enhancement Lab (B-1232A),1946,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |209,NASA 2,Langley Research Center,Public,Quality Assurance & Inspection Lab/1232A,1946,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |210,NASA 2,Langley Research Center,Public,Radiation Physics Computer Laboratory,1965,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |211,NASA 2,Langley Research Center,Public,Research Laboratory,1967,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |212,NASA 2,Langley Research Center,Public,Semi-Anecho Electromagnetic Comp Test Complex/B1220,1945,Active,,6/22/2006,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |213,NASA 2,Langley Research Center,Public,Structural Acoustic Loads & Transmission Facility,,Active,http://stab.larc.nasa.gov/Fac-SALT.html,8/1/2012,8/2/2012,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |214,NASA 2,Langley Research Center,Public,Structural Dynamics Research Lab,1963,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |215,NASA 2,Langley Research Center,Public,Structures and Materials Lab,1940,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |216,NASA 2,Langley Research Center,Public,Thermal Acoustic Fatigue Apparatus,,Active,http://stab.larc.nasa.gov/Fac-TAFA.html,8/2/2012,8/2/2012,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |217,NASA 2,Langley Research Center,Public,Thermal Structures Lab,1960,Active,,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |218,NASA 2,Langley Research Center,Public,Transonic Dynamics Tunnel,1960,Active,http://www.aeronautics.nasa.gov/atp/index.html,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |219,NASA 2,Langley Research Center,Public,Unitary Plan Wind Tunnel,1952,Active,http://www.aeronautics.nasa.gov/atp/index.html,3/1/1996,2/25/2013,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
      |220,NASA 2,Langley Research Center,Public,Vibration Laboratory,,Active,http://engineering.larc.nasa.gov/vibration_lab.html,8/1/2012,8/1/2012,NASA Langley Research Center,Hampton,VA,23681-2199,US,Sherry Johnson,241,757.864-3848
    """.stripMargin
  private val nasaMtoMData =
    """221,NASA 2,Marshall Space Flight Center,Public,18-Ft Vacuum Coating Chamber - 4707,,Active,,7/16/2010,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |222,NASA 2,Marshall Space Flight Center,Public,"AB Communications Facility, Bldg. 4207",1962,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |223,NASA 2,Marshall Space Flight Center,Public,Composites Development Facility 4707,1956,Active,,3/1/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |224,NASA 2,Marshall Space Flight Center,Public,Cosmic Ray Emulsion Laboratory 4481,1991,Inactive,,3/1/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |225,NASA 2,Marshall Space Flight Center,Public,EB Audio Reverberant Facility 4477,1991,Active,,3/1/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |226,NASA 2,Marshall Space Flight Center,Public,EB Electrical Power Systems Test Fac 4475,1964,Active,,3/1/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |227,NASA 2,Marshall Space Flight Center,Public,EB Marshall Avionics Sys. Testbed (MAST) 4487,1963,Active,,3/1/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |228,NASA 2,Marshall Space Flight Center,Public,EB Precision Optical Facility 4487,1968,Active,,3/1/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |229,NASA 2,Marshall Space Flight Center,Public,EB Radio Frequency Development Lab 4189,1957,Active,,3/1/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |230,NASA 2,Marshall Space Flight Center,Public,EB Solar Array Test Facility 4475,1964,Active,,3/1/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |231,NASA 2,Marshall Space Flight Center,Public,EB Straylight Facility 4487,1957,Active,,3/1/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |232,NASA 2,Marshall Space Flight Center,Public,EB Doppler Lidar Facility 4467,1957,Active,,3/1/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |233,NASA 2,Marshall Space Flight Center,Public,EB LAB Anechoic Test Chamber 4190,1965,Active,,6/10/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |234,NASA 2,Marshall Space Flight Center,Public,EB LAB Control Moment Gyro Test & Eval Fac 4487,,Active,,6/11/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |235,NASA 2,Marshall Space Flight Center,Public,ED Air Flow Turbine Test Facility 4777,1985,Active,,3/1/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |236,NASA 2,Marshall Space Flight Center,Public,ED Coldflow R & D Test Complex 4776,1985,Active,,3/1/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |237,NASA 2,Marshall Space Flight Center,Public,ED Liquid Rocket Engine Air Flow Fac 4777,1985,Active,,3/1/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |238,NASA 2,Marshall Space Flight Center,Public,ED Liquid/Solid Rocket Eng Water Flow Fac 4776,1985,Active,,3/1/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |239,NASA 2,Marshall Space Flight Center,Public,ED Pump Test Equipment Facility 4777,1995,Active,,5/30/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |240,NASA 2,Marshall Space Flight Center,Public,ED Solid Rocket Engine Air Flow Fac 4777,1985,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |241,NASA 2,Marshall Space Flight Center,Public,ED Water Flow Inducer Test Loop 4777,1985,Active,,3/1/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |242,NASA 2,Marshall Space Flight Center,Public,EH Advanced Bonding Technology Laboratory 4707,1956,Active,,3/1/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |243,NASA 2,Marshall Space Flight Center,Public,EH Ambient & High Pressure LOX/GOX Facility 4623,1960,Active,,3/1/1996,5/30/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |244,NASA 2,Marshall Space Flight Center,Public,EH Ceramics Coatings & High Temp Mat.Lab 4612,1960,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |245,NASA 2,Marshall Space Flight Center,Public,EH Computer Aided Design (CAD)Room 4708,1989,Active,,5/29/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |246,NASA 2,Marshall Space Flight Center,Public,EH Computerized Data Base System 4612,1960,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |247,NASA 2,Marshall Space Flight Center,Public,EH Environmental Replacement Tech. Lab 4707,1956,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |248,NASA 2,Marshall Space Flight Center,Public,EH Foam Formulation Laboratory 4739,1956,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |249,NASA 2,Marshall Space Flight Center,Public,EH Frictional Heating Test System 4623,1960,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |250,NASA 2,Marshall Space Flight Center,Public,EH Hazardous Machining Facility 4759,1958,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |251,NASA 2,Marshall Space Flight Center,Public,EH Hazardous Tribological Test Facility 4643,1960,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |252,NASA 2,Marshall Space Flight Center,Public,EH Heat Treat Shop 4704,1958,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |253,NASA 2,Marshall Space Flight Center,Public,EH High-Temperature Heat Treating Fac 4767,1958,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |254,NASA 2,Marshall Space Flight Center,Public,EH Inert Propellant Development Lab 4767,1960,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |255,NASA 2,Marshall Space Flight Center,Public,EH Low Pressure Flammability Test Sys. 4623,1960,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |256,NASA 2,Marshall Space Flight Center,Public,EH Mechanical & Corrosion Testing 4612,1961,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |257,NASA 2,Marshall Space Flight Center,Public,EH Metallurgical Diagnostics Laboratory 4612,1961,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |258,NASA 2,Marshall Space Flight Center,Public,EH Metals Processing Facility 4711,1958,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |259,NASA 2,Marshall Space Flight Center,Public,EH Non-Optical NDE Facility 4702,,Active,,5/28/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |260,NASA 2,Marshall Space Flight Center,Public,EH Plasma Arc Metal Cutting Facility 4738,1958,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |261,NASA 2,Marshall Space Flight Center,Public,EH Robotic Welding Facility 4707,1956,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |262,NASA 2,Marshall Space Flight Center,Public,EH Robotic Workcell 4707,1956,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |263,NASA 2,Marshall Space Flight Center,Public,EH Rubber Fabrication Laboratory 4612,1960,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |264,NASA 2,Marshall Space Flight Center,Public,EH Sheet Metal Assembly & Machine Shop 4705,1958,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |265,NASA 2,Marshall Space Flight Center,Public,EH Spray-On Foam Insulation (SOFI) Lab 4707,1956,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |266,NASA 2,Marshall Space Flight Center,Public,EH SRB-TPS (Thermal Protection Sys) Dev. 470,1956,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |267,NASA 2,Marshall Space Flight Center,Public,EH Surface Treatment Facility 4760,1960,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |268,NASA 2,Marshall Space Flight Center,Public,EH Vacuum Plasma Spray Facility 4707,1956,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |269,NASA 2,Marshall Space Flight Center,Public,EH High Pres. Ignit. Promoted Combust.Test System,1960,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |270,NASA 2,Marshall Space Flight Center,Public,EI Manned Habitat ECLSS Test Facility 4755,1985,Active,,5/17/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |271,NASA 2,Marshall Space Flight Center,Public,EI Space Systems Integration and Test Facility 4,1992,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |272,NASA 2,Marshall Space Flight Center,Public,EI MSFC Electromagnetic Interference (EMI) Test Fa,1995,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |273,NASA 2,Marshall Space Flight Center,Public,EL Ground Control Experiment Laboratory 4708,1958,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |274,NASA 2,Marshall Space Flight Center,Public,EL Systems and Payloads Test Facilities 4708,1958,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |275,NASA 2,Marshall Space Flight Center,Public,"EL X-Ray & Cryogenic Facility (XRCF), 4718",1991,Active,http://optics.nasa.gov/facilities/xraycal.html,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |276,NASA 2,Marshall Space Flight Center,Public,EM Contamination Control Laboratories 4711,,Active,,5/29/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |277,NASA 2,Marshall Space Flight Center,Public,EM SEE: Atomic Oxygen & Solar UV Lab 4711,,Active,,5/29/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |278,NASA 2,Marshall Space Flight Center,Public,EM Space Environmental Effects Facility 4605,1970,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |279,NASA 2,Marshall Space Flight Center,Public,"EM Bearing Test Facility, Bldg 4711",1960,Active,,5/14/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |280,NASA 2,Marshall Space Flight Center,Public,"EM Chemistry Laboratories, Building 4612",1957,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |281,NASA 2,Marshall Space Flight Center,Public,EM Electrostatic Levitator Materials Characterizat,,Active,,8/4/2006,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |282,NASA 2,Marshall Space Flight Center,Public,"EM Environmental Gas/Toxic Offgassing Lab, Bldg 46",1960,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |283,NASA 2,Marshall Space Flight Center,Public,EM Hydrogen (H2) Test Facility 4628,1961,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |284,NASA 2,Marshall Space Flight Center,Public,"EM Impact Testing Facility 4571, 4612",2005,Active,,8/4/2006,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |285,NASA 2,Marshall Space Flight Center,Public,"EM Long-Duration Lubricant Test Laboratory, Bldg 4",,Active,,8/9/2006,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |286,NASA 2,Marshall Space Flight Center,Public,"EM Materials Combustion Research Facility, Bldg 46",,Active,,8/9/2006,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |287,NASA 2,Marshall Space Flight Center,Public,"EM Mechanical Materials Test Facility, Bldg 4612",,Active,,8/8/2006,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |288,NASA 2,Marshall Space Flight Center,Public,"EM Metrology Laboratory, Building 4711",,Active,,8/9/2006,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |289,NASA 2,Marshall Space Flight Center,Public,"EM Tribology Test Laboratory, Building 4711",1960,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |290,NASA 2,Marshall Space Flight Center,Public,EM-20 Computed Tomography Facility 4707,1985,Active,,3/1/1996,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |291,NASA 2,Marshall Space Flight Center,Public,EM-20 Microwave NDE Facility 4702,,Active,,8/11/2006,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |292,NASA 2,Marshall Space Flight Center,Public,EM-20 Acoustic Emission Facility 4711,1992,Active,,8/4/2006,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |293,NASA 2,Marshall Space Flight Center,Public,EM-20 Automated Ultrasonics Laboratory 4702,,Active,,8/11/2006,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |294,NASA 2,Marshall Space Flight Center,Public,EM-20 Backscatter Radiography Facility 4711/4707,2002,Active,,8/4/2006,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |295,NASA 2,Marshall Space Flight Center,Public,EM-20 Contact Ultrasonics Laboratory 4702,,Active,,8/11/2006,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |296,NASA 2,Marshall Space Flight Center,Public,EM-20 Digital X-ray Laminography 4711,2006,Active,,8/4/2006,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |297,NASA 2,Marshall Space Flight Center,Public,EM-20 Film Radiography Facility 4702,,Active,,8/11/2006,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |298,NASA 2,Marshall Space Flight Center,Public,"EM-20 Penetrant, Optical and Magnetic Particle Lab",,Active,,8/11/2006,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |299,NASA 2,Marshall Space Flight Center,Public,EM-20 Phased Array Ultrasonic Laboratory 4702,,Active,,8/11/2006,6/2/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |300,NASA 2,Marshall Space Flight Center,Public,EM-20 Shearography Facility 4711,1992,Active,,8/4/2006,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |301,NASA 2,Marshall Space Flight Center,Public,EM-20 Terahertz Imaging Facility 4711,2003,Active,,8/4/2006,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |302,NASA 2,Marshall Space Flight Center,Public,EM-20 Thermography Facility 4711,1992,Active,,8/4/2006,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |303,NASA 2,Marshall Space Flight Center,Public,EO Huntsville Operations Support Center 4663,1959,Active,http://ed.msfc.nasa.gov/edinside/eo,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |304,NASA 2,Marshall Space Flight Center,Public,EO UDC/ Express Rack/ Express Pallet 4612,1988,Under,,5/29/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |305,NASA 2,Marshall Space Flight Center,Public,EP Cold Flow Position 4670 ,1965,,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |306,NASA 2,Marshall Space Flight Center,Public,EP Data Recording and Control Facility 4674 ,1964,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |307,NASA 2,Marshall Space Flight Center,Public,EP Data Recording Facility 4583 ,1957,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |308,NASA 2,Marshall Space Flight Center,Public,EP Hot Gas Test Facility 4554 ,1976,,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |309,NASA 2,Marshall Space Flight Center,Public,EP Hydrocarbon (HC) Engine Test Fac 4696,1963,Inactive,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |310,NASA 2,Marshall Space Flight Center,Public,EP Propulsion Technology Test Facility 4670,1965,,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |311,NASA 2,Marshall Space Flight Center,Public,EP Solid Propulsion Test Facility 4520,,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |312,NASA 2,Marshall Space Flight Center,Public,EP Test Cells 4583 ,1957,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |313,NASA 2,Marshall Space Flight Center,Public,EP Test Facility (TF116) 4540,1964,Active,http://rockettest.nasa.gov,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |314,NASA 2,Marshall Space Flight Center,Public,EP Test Facility (TS300) 4530,1966,Active,,6/5/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |315,NASA 2,Marshall Space Flight Center,Public,EP Transient Pressure Test Facility 4515 ,1987,Inactive,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |316,NASA 2,Marshall Space Flight Center,Public,EP Mechanisms Development Facility 4656 ,1965,,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |317,NASA 2,Marshall Space Flight Center,Public,EP Test Facility (TF500) 4522 ,1966,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |318,NASA 2,Marshall Space Flight Center,Public,ES BATSE Operations & Data Analysis Lab 4481,1990,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |319,NASA 2,Marshall Space Flight Center,Public,ES Dist Active Archive Center 917 Expl. Blvd.,1991,,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |320,NASA 2,Marshall Space Flight Center,Public,ES Earth Science & Appl.Computer Facility 4492,1991,,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |321,NASA 2,Marshall Space Flight Center,Public,ES Laboratory for Structural Biology 4484 ,1994,,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |322,NASA 2,Marshall Space Flight Center,Public,ES Low Energy Ion Facility 4481 ,1974,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |323,NASA 2,Marshall Space Flight Center,Public,ES Plasma Wind Tunnel 4481,1991,,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |324,NASA 2,Marshall Space Flight Center,Public,ES Solar Magnetograph Facility 4347 ,1950,,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |325,NASA 2,Marshall Space Flight Center,Public,ET Acoustic Test Facility 4619,1959,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |326,NASA 2,Marshall Space Flight Center,Public,ET Component/System Quasi-Static Load Fac 4619,1959,Active,,4/25/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |327,NASA 2,Marshall Space Flight Center,Public,ET Cryogenic Structural Test Facility 4699 ,,Active,,5/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |328,NASA 2,Marshall Space Flight Center,Public,ET East Vibration Test Facility 4619,,Active,,7/6/2010,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |329,NASA 2,Marshall Space Flight Center,Public,ET Flight Environmental Test Facility 4619,1962,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |330,NASA 2,Marshall Space Flight Center,Public,ET Hazardous Structural Test Complex 4572 ,,Active,,5/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |331,NASA 2,Marshall Space Flight Center,Public,ET Large Structure Quasi-Static Load Fac 4619,,Active,,4/25/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |332,NASA 2,Marshall Space Flight Center,Public,ET Modal Test Facility 4619,,Active,,4/25/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |333,NASA 2,Marshall Space Flight Center,Public,ET Pyrotechnic Shock Test Facility 4619,,Active,,4/25/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |334,NASA 2,Marshall Space Flight Center,Public,ET Structural Dynamics Control Facility 4619,,Active,,5/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |335,NASA 2,Marshall Space Flight Center,Public,ET West Vibration Test Facility 4619,,Active,,4/25/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |336,NASA 2,Marshall Space Flight Center,Public,ET WTC - Wind Tunnel Complex 4732,1968,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |337,NASA 2,Marshall Space Flight Center,Public,ET WTC - 14-Inch Trisonic Wind Tunnel 4732,1968,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |338,NASA 2,Marshall Space Flight Center,Public,ET Dynamics Test Stand (4550),,Inactive,,8/11/2006,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |339,NASA 2,Marshall Space Flight Center,Public,EV 400-Ft. Antenna Test Range 4194,1962,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |340,NASA 2,Marshall Space Flight Center,Public,"EV Engine Hardware Simulation Laboratory, 4436",1988,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |341,NASA 2,Marshall Space Flight Center,Public,EV Flight Simulation Lab 4476 ,1963,Inactive,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |342,NASA 2,Marshall Space Flight Center,Public,EV Battery Test Laboratory 4475 ,1964,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |343,NASA 2,Marshall Space Flight Center,Public,EV Contact Dynamics Simulation Lab (6DOF) 4663,1980,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |344,NASA 2,Marshall Space Flight Center,Public,EV Flight Robotics Laboratory (Flat Floor) 4619,1983,Active,,6/3/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |345,NASA 2,Marshall Space Flight Center,Public,EV LAB 1/2 Mile Antenna Test Range 4194 ,1976,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |346,NASA 2,Marshall Space Flight Center,Public,Fiber Placement Fac 4707 ,1956,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |347,NASA 2,Marshall Space Flight Center,Public,Filament Winding Lab 4707 ,1956,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |348,NASA 2,Marshall Space Flight Center,Public,Low Gravity Materials Science Facility 4550,1991,Inactive,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |349,NASA 2,Marshall Space Flight Center,Public,Materials Testing & Research Facility 4464,,Active,,3/8/2007,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |350,NASA 2,Marshall Space Flight Center,Public,Neutral Buoyancy Facility 4705 ,1968,Inactive,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |351,NASA 2,Marshall Space Flight Center,Public,Propulsion Research and Development Laboratory,2005,Active,,8/10/2006,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |352,NASA 2,Marshall Space Flight Center,Public,Stray Light Vacuum Chamber Facility 4487,,Active,,7/17/2010,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |353,NASA 2,Marshall Space Flight Center,Public,Subscale Rocket Motor Processing Fac 4707,1956,Active,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |354,NASA 2,Marshall Space Flight Center,Public,Sun Spot Thermal Vacuum Chamber - 4619,,Active,,7/18/2010,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |355,NASA 2,Marshall Space Flight Center,Public,V20 Thermal Vacuum Chamber - 4619,,Active,,7/18/2010,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |356,NASA 2,Marshall Space Flight Center,Public,Vacuum Drop Tube Facility 4550 ,1965,Inactive,,3/1/1996,3/31/2014,MARSHALL SPACE FLIGHT CENTER,MSFC,AL,35812,US,Roslin HIcks,AS21/4250,256-544-7795
      |357,NASA 2,Michoud Assembly Facility,Public,Chemical Cleaning Facility,1943,Active,http://mafbusiness.msfc.nasa.gov/,1/28/2009,2/19/2013,PO Box 29300,New Orleans,LA,70189,US,Ernest Graham,AS60,504.257-2619
      |358,NASA 2,Michoud Assembly Facility,Public,Component Ablator Facility Mix Room,1985,Active,http://mafbusiness.msfc.nasa.gov/,4/6/2009,2/19/2013,PO Box 29300,New Orleans,LA,70189,US,Ernest Graham,AS60,504.257-2619
      |359,NASA 2,Michoud Assembly Facility,Public,Component Ablator Facility SLA Spray Preparation Area,1985,Active,http://mafbusiness.msfc.nasa.gov/,4/6/2009,2/19/2013,PO Box 29300,New Orleans,LA,70189,US,Ernest Graham,AS60,504.257-2619
      |360,NASA 2,Michoud Assembly Facility,Public,Component Ablator Facility SLA Spray Room,1985,Active,http://mafbusiness.msfc.nasa.gov/,4/7/2009,2/19/2013,PO Box 29300,New Orleans,LA,70189,US,Ernest Graham,AS60,504.257-2619
      |361,NASA 2,Michoud Assembly Facility,Public,Component Painting Facility,,Active,http://mafbusiness.msfc.nasa.gov/,2/2/2009,2/19/2013,PO Box 29300,New Orleans,LA,70189,US,Ernest Graham,AS60,504.257-2619
      |362,NASA 2,Michoud Assembly Facility,Public,Friction Stir Welding Facility,1943,Active,http://mafbusiness.msfc.nasa.gov/,2/9/2009,2/19/2013,PO Box 29300,New Orleans,LA,70189,US,Ernest Graham,AS60,504.257-2619
      |363,NASA 2,Michoud Assembly Facility,Public,Harness Fabrication Facility,1943,Active,http://mafbusiness.msfc.nasa.gov/,2/2/2009,2/19/2013,PO Box 29300,New Orleans,LA,70189,US,Ernest Graham,AS60,504.257-2619
      |364,NASA 2,Michoud Assembly Facility,Public,Heat Treating Facility,1943,Active,http://mafbusiness.msfc.nasa.gov/,2/2/2009,2/19/2013,PO Box 29300,New Orleans,LA,70189,US,Ernest Graham,AS60,504.257-2619
      |365,NASA 2,Michoud Assembly Facility,Public,High Bay Facility,1982,Active,http://mafbusiness.msfc.nasa.gov/,2/3/2009,2/19/2013,PO Box 29300,New Orleans,LA,70189,US,Ernest Graham,AS60,504.257-2619
      |366,NASA 2,Michoud Assembly Facility,Public,LH2 Ablator Spray Facility,1980,Active,http://mafbusiness.msfc.nasa.gov/,4/6/2009,2/19/2013,PO Box 29300,New Orleans,LA,70189,US,Ernest Graham,AS60,504.257-2619
      |367,NASA 2,Michoud Assembly Facility,Public,Machine Shop Facility,1943,Active,http://mafbusiness.msfc.nasa.gov/,2/2/2009,2/19/2013,PO Box 29300,New Orleans,LA,70189,US,Ernest Graham,AS60,504.257-2619
      |368,NASA 2,Michoud Assembly Facility,Public,Major Component Cleaning Facility,1943,Inactive,http://mafbusiness.msfc.nasa.gov/,2/2/2009,2/19/2013,PO Box 29300,New Orleans,LA,70189,US,Ernest Graham,AS60,504.257-2619
      |369,NASA 2,Michoud Assembly Facility,Public,Port Michoud Facility,1963,Active,http://mafbusiness.msfc.nasa.gov/,1/29/2009,2/19/2013,PO Box 29300,New Orleans,LA,70189,US,Ernest Graham,AS60,504.257-2619
      |370,NASA 2,Michoud Assembly Facility,Public,Precision Cleaning Facility,1943,Active,http://mafbusiness.msfc.nasa.gov/,2/3/2009,2/19/2013,PO Box 29300,New Orleans,LA,70189,US,Ernest Graham,AS60,504.257-2619
      |371,NASA 2,Michoud Assembly Facility,Public,Riveting Facility,1943,Active,http://mafbusiness.msfc.nasa.gov/,2/9/2009,2/19/2013,PO Box 29300,New Orleans,LA,70189,US,Ernest Graham,AS60,504.257-2619
      |372,NASA 2,Michoud Assembly Facility,Public,Thermal Protection System Area,1963,Active,http://mafbusiness.msfc.nasa.gov/,4/6/2009,2/19/2013,PO Box 29300,New Orleans,LA,70189,US,Ernest Graham,AS60,504.257-2619
      |373,NASA 2,Michoud Assembly Facility,Public,Vertical Assembly Facility,1963,Active,http://mafbusiness.msfc.nasa.gov/,2/3/2009,2/19/2013,PO Box 29300,New Orleans,LA,70189,US,Ernest Graham,AS60,504.257-2619
    """.stripMargin
  private val nasaNtoRData =
    """374,NASA 2,NASA Aircraft Management Division,Public,ARC Scientific Instrumentation Evaluation Remote Research Aircraft (SIERRA),,Active,,11/4/2009,7/22/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |375,NASA 2,NASA Aircraft Management Division,Public,DFRC B-200 Super Kingair Research/Support Aircraft,,Active,http://www.nasa.gov/centers/dryden/aircraft/KingAir/index.html,11/4/2009,2/13/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |376,NASA 2,NASA Aircraft Management Division,Public,DFRC DC-8 Earth Science Research Aircraft,,Active,http://www.nasa.gov/centers/dryden/aircraft/DC-8/index.html,10/7/2009,2/13/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |377,NASA 2,NASA Aircraft Management Division,Public,DFRC ER-2 High Altitude Research Aircraft,,Active,http://www.nasa.gov/centers/dryden/aircraft/ER-2/index.html,9/8/2009,2/13/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |378,NASA 2,NASA Aircraft Management Division,Public,DFRC F/A-18 Research Aircraft,,Active,http://www.nasa.gov/centers/dryden/aircraft/F-18_853/index.html,11/4/2009,2/13/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |379,NASA 2,NASA Aircraft Management Division,Public,DFRC F-15B/D Research Aircraft,,Active,http://www.nasa.gov/centers/dryden/aircraft/F-15B/index.html,11/4/2009,2/13/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |380,NASA 2,NASA Aircraft Management Division,Public,DFRC Gulfstream G-III Research Aircraft,,Active,http://www.nasa.gov/centers/dryden/aircraft/G-III_UAVSAR/index.html,10/7/2009,2/13/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |381,NASA 2,NASA Aircraft Management Division,Public,DFRC Ikhana (Predator B) Unmanned Aerial System,,Active,http://www.nasa.gov/centers/dryden/aircraft/Ikhana/index.html,10/7/2009,2/13/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |382,NASA 2,NASA Aircraft Management Division,Public,DFRC RQ-4 Global Hawk Unmanned Aerial System,,Active,http://www.nasa.gov/centers/dryden/aircraft/GlobalHawk/index.html,10/7/2009,2/13/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |383,NASA 2,NASA Aircraft Management Division,Public,DFRC Stratospheric Observatory for Infrared Astronomy (SOFIA) Aircraft,,Active,http://www.nasa.gov/centers/dryden/news/FactSheets/FS-096-DFRC.html,10/7/2009,2/13/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |384,NASA 2,NASA Aircraft Management Division,Public,GRC DHC-6 Twin Otter Research Aircraft,,Active,http://icebox-esn.grc.nasa.gov/facilities/otter.html,11/4/2009,7/22/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |385,NASA 2,NASA Aircraft Management Division,Public,GRC Learjet 25 Research Aircraft,,Active,,11/4/2009,7/22/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |386,NASA 2,NASA Aircraft Management Division,Public,GRC S-3B Viking Research Aircraft,,Active,,11/4/2009,7/22/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |387,NASA 2,NASA Aircraft Management Division,Public,JSC B-377G Super Guppy Support Aircraft,,Active,http://jsc-aircraft-ops.jsc.nasa.gov/guppy/index.html,11/4/2009,7/22/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |388,NASA 2,NASA Aircraft Management Division,Public,JSC WB-57 High Altitude Research Aircraft,,Active,http://jsc-aircraft-ops.jsc.nasa.gov/wb57/index.html,11/4/2009,7/22/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |389,NASA 2,NASA Aircraft Management Division,Public,LaRC B-200 Super King Air Research Aircraft,,Active,,11/4/2009,7/22/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |390,NASA 2,NASA Aircraft Management Division,Public,LaRC Cessna 206 Stationair Research Aircraft,,Active,,11/4/2009,7/22/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |391,NASA 2,NASA Aircraft Management Division,Public,LaRC Cirrus SR-22 Research Aircraft,,Active,,11/4/2009,7/22/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |392,NASA 2,NASA Aircraft Management Division,Public,LaRC HU-25C Falcon Research Aircraft,,Active,http://airbornescience.nasa.gov/aircraft/HU-25C_Falcon,11/4/2009,7/22/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |393,NASA 2,NASA Aircraft Management Division,Public,LaRC Lancair Columbia 300 Research Aircraft,,Inactive,,11/4/2009,2/13/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |394,NASA 2,NASA Aircraft Management Division,Public,LaRC OV-10G Research Aircraft,,Inactive,,11/4/2009,2/13/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |395,NASA 2,NASA Aircraft Management Division,Public,WFF C-23 Sherpa Airborne Research Aircraft,,Active,http://airbornescience.nasa.gov/aircraft/C-23_Sherpa,11/4/2009,2/13/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |396,NASA 2,NASA Aircraft Management Division,Public,WFF P-3B Research Aircraft,,Active,http://wacop.wff.nasa.gov/LAAPBDesc.cfm,11/4/2009,2/13/2013,"300 E St, SW",Washington,DC,20546,US,Hsien (Shen) Yen,LD010,202 358 4721
      |397,NASA 2,Stennis Space Center,Public,A Complex Test Control Center (TCC) #4110,1966,Active,,3/1/1996,9/2/2014,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |398,NASA 2,Stennis Space Center,Public,B Complex Test Control Center (TCC) #4210,1966,Active,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |399,NASA 2,Stennis Space Center,Public,"Barge, Liquid Hydrogen (3 each)",1965,Active,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |400,NASA 2,Stennis Space Center,Public,"Barge, Liquid Oxygen (6 each)",1965,Active,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |401,NASA 2,Stennis Space Center,Public,"Canals, Lock & Dock System #0036,2310,2311,2317",1966,Active,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |402,NASA 2,Stennis Space Center,Public,Communications Building #1201,1965,Active,,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |403,NASA 2,Stennis Space Center,Public,Cryogenics Docks #0008,1965,Active,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |404,NASA 2,Stennis Space Center,Public,Data Acquisition Facility (DAF) #4995,1966,Active,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |405,NASA 2,Stennis Space Center,Public,Electrical Distribution System #0010,1964,Active,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |406,NASA 2,Stennis Space Center,Public,Emergency Power Generating System #4400,1966,Active,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |407,NASA 2,Stennis Space Center,Public,Engine Processing Building #3202,1965,Inactive,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |408,NASA 2,Stennis Space Center,Public,"High Pressure Gas Facility #0041,3305,6,9,10,11",1966,Active,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |409,NASA 2,Stennis Space Center,Public,High Pressure Industrial Water Plant #4400,1966,Active,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |410,NASA 2,Stennis Space Center,Public,Hydrogen Transfer Facility #3415,1965,Active,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |411,NASA 2,Stennis Space Center,Public,Industrial Water Reservoir #4325,1966,Active,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |412,NASA 2,Stennis Space Center,Public,Measurement Standards & Calibration Lab #8100,1966,Active,,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |413,NASA 2,Stennis Space Center,Public,Propellant Transfer & Storage Facility #3414,1965,Active,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |414,NASA 2,Stennis Space Center,Public,Push Boat,1965,Active,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |415,NASA 2,Stennis Space Center,Public,"Repair, Fab. & Fluid Component Proc. #2201,2205,5A",1964,Active,,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |416,NASA 2,Stennis Space Center,Public,Science Laboratory #8110,1966,Active,,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |417,NASA 2,Stennis Space Center,Public,Sensor Laboratory #1105,1966,Active,,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |418,NASA 2,Stennis Space Center,Public,Test Facility E-1 #4050,1992,Active,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |419,NASA 2,Stennis Space Center,Public,Test Facility E-2 #4001,1994,Active,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |420,NASA 2,Stennis Space Center,Public,Test Facility E-3 #4071,,Active,http://rockettest.nasa.gov,12/11/2000,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |421,NASA 2,Stennis Space Center,Public,Test Facility H-1 #5001,1995,Inactive,,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |422,NASA 2,Stennis Space Center,Public,Test Operations Bldg. E Complex #4010,1991,Active,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |423,NASA 2,Stennis Space Center,Public,Test Stand A-1 #4120,1967,Active,http://rockettest.nasa.gov,3/1/1996,1/30/2012,"PA10-Planning, Development, & Analysis Office",Stennis Space Center,MS,39529-6000,US,Robert Bruce,PA10,601-688-1646
      |424,NASA 2,Wallops Flight Facility/GSFC,Public,Launch Range,1959,Active,http://www.nasa.gov/centers/wallops,3/1/1996,7/11/2013,Code 802,Wallops Island,VA,23337-5099,US,Scott H. Schaire,F-6,757-824-1120
      |425,NASA 2,Wallops Flight Facility/GSFC,Public,Main Base Laboratories and Shops,1959,Active,http://www.nasa.gov/centers/wallops,3/1/1996,7/19/2013,Code 802,Wallops Island,VA,23337-5099,US,Scott H. Schaire,F-6,757-824-1120
      |426,NASA 2,Wallops Flight Facility/GSFC,Public,McMurdo Ground Station (MGS),1994,Active,http://www.nasa.gov/centers/wallops,3/1/1996,7/19/2013,Code 802,Wallops Island,VA,23337-5099,US,Scott H. Schaire,F-6,757-824-1120
      |427,NASA 2,Wallops Flight Facility/GSFC,Public,Meteorological Facilities and ASRF,1959,Active,http://www.nasa.gov/centers/wallops,3/1/1996,7/19/2013,Code 802,Wallops Island,VA,23337-5099,US,Scott H. Schaire,F-6,757-824-1120
      |428,NASA 2,Wallops Flight Facility/GSFC,Public,Mobile Range Systems,1960,Active,http://www.nasa.gov/centers/wallops,3/1/1996,7/19/2013,Code 802,Wallops Island,VA,23337-5099,US,Scott H. Schaire,F-6,757-824-1120
      |429,NASA 2,Wallops Flight Facility/GSFC,Public,National Scientific Balloon Facility,1977,Active,http://www.nasa.gov/centers/wallops,3/1/1996,7/19/2013,Code 802,Wallops Island,VA,23337-5099,US,Scott H. Schaire,F-6,757-824-1120
      |430,NASA 2,Wallops Flight Facility/GSFC,Public,Poker Flat Research Range & SAR Facility,1970,Active,http://www.nasa.gov/centers/wallops,3/1/1996,7/19/2013,Code 802,Wallops Island,VA,23337-5099,US,Scott H. Schaire,F-6,757-824-1120
      |431,NASA 2,Wallops Flight Facility/GSFC,Public,Wallops Orbital Tracking Station,1960,Active,http://www.nasa.gov/centers/wallops,3/1/1996,7/19/2013,Code 802,Wallops Island,VA,23337-5099,US,Scott H. Schaire,F-6,757-824-1120
      |432,NASA 2,Wallops Flight Facility/GSFC,Public,WFF Research Airport,1958,Active,http://www.nasa.gov/centers/wallops,3/1/1996,7/19/2013,Code 802,Wallops Island,VA,23337-5099,US,Scott H. Schaire,F-6,757-824-1120
      |433,NASA 2,Wallops Flight Facility/GSFC,Public,White Sands Missile Range,1964,Active,http://www.nasa.gov/centers/wallops,3/1/1996,7/19/2013,Code 802,Wallops Island,VA,23337-5099,US,Scott H. Schaire,F-6,757-824-1120
      |434,OSC,Orbital Satellite Manufacturing Facility - Arizona,Public,15 X 20 Thermal Vacuum Test Facility,,Active,http://www.orbital.com/NewsInfo/Publications/SMF_Arizona.pdf,11/22/2011,8/28/2013,1721 Elliot Road,Gibert,AZ,85223,US,"GaryRico, Lead Thermal Vacuum Test Engineer",N/A,480 355 7275
      |435,RAYTHEON,Raytheon Space and Airborne Systems - El Segundo,Public,Anechoic Chambers,,Active,http://www.raytheon.com/capabilities/products/labs/,6/26/2012,9/12/2013,2000 E. El Segundo Blvd.,El Segundo,CA,90245,US,Paul N. Stovner,A130,310-647-4171
      |436,RAYTHEON,Raytheon Space and Airborne Systems - El Segundo,Public,Climatics Test Area,,Active,http://www.raytheon.com/capabilities/products/labs/,6/26/2012,9/12/2013,2000 E. El Segundo Blvd.,El Segundo,CA,90245,US,Paul N. Stovner,A130,310-647-4171
      |437,RAYTHEON,Raytheon Space and Airborne Systems - El Segundo,Public,Component Test and Analysis Laboratories,,Active,http://www.raytheon.com/capabilities/products/ctal/,6/26/2012,6/26/2012,2000 E. El Segundo Blvd.,El Segundo,CA,90245,US,Paul N. Stovner,A130,310-647-4171
      |438,RAYTHEON,Raytheon Space and Airborne Systems - El Segundo,Public,Sensor Thermal Vacuum Chamber - Integrated Test Facility,,Active,http://www.raytheon.com/capabilities/products/labs/,6/26/2012,6/26/2012,2000 E. El Segundo Blvd.,El Segundo,CA,90245,US,Paul N. Stovner,A130,310-647-4171
      |439,RAYTHEON,Raytheon Space and Airborne Systems - El Segundo,Public,Vibration Test Laboratory,,Active,http://www.raytheon.com/capabilities/products/labs/,6/26/2012,9/12/2013,2000 E. El Segundo Blvd.,El Segundo,CA,90245,US,Paul N. Stovner,A130,310-647-4171
      |""".stripMargin

  def getTmpDatafileName: String = {
    val bufferedWriter = (new FileWriter(dataFile))
    bufferedWriter.write(dodData)
    bufferedWriter.write(intelsatData)
    bufferedWriter.write(nasaAtoGData)
    bufferedWriter.write(nasaJtoLData)
    bufferedWriter.write(nasaMtoMData)
    bufferedWriter.write(nasaNtoRData)
    bufferedWriter.close()
    dataFile.getAbsolutePath
  }

  def getResourceDataFilePath(dataFileName: String): String = {
    getClass
      .getClassLoader()
      .getResource(dataFileName)
      .getPath
  }

  def cleanupDataFiles = {
    dataFile.delete
  }
}
