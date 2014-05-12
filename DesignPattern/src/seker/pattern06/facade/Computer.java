/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.pattern06.facade;

import seker.pattern06.facade.packages.CPU;
import seker.pattern06.facade.packages.HardDrive;
import seker.pattern06.facade.packages.Memory;

/**
 * Facade类
 * 
 * @author liuxinjian
 * @since 2013-1-26
 */
public class Computer {

    CPU cpu = new CPU();
    Memory memory = new Memory();
    HardDrive hardDrive = new HardDrive();
    
    public void startComputer() {
        final long BOOT_ADDRESS = 0;
        final long BOOT_SECTOR = 0;
        final int SECTOR_SIZE = 100;
        
        cpu.freeze();

        memory.load(BOOT_ADDRESS, hardDrive.read(BOOT_SECTOR, SECTOR_SIZE));

        cpu.jump(BOOT_ADDRESS);

        cpu.execute();
    }

}
