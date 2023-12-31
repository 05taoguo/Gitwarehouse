package cn.jzyh.service.impl;

import cn.jzyh.entity.AddressBook;
import cn.jzyh.mapper.AddressBookMapper;
import cn.jzyh.service.AddressBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
