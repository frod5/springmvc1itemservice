package hello.itemservice.domain.item;

import hello.itemservice.domain.Item;
import hello.itemservice.domain.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA",10000,10);

        //when
        Item saveItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        Assertions.assertThat(saveItem).isEqualTo(findItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("itemA",10000,10);
        Item item2 = new Item("itemB",20000,20);

        //when
        itemRepository.save(item1);
        itemRepository.save(item2);

        //then
        List<Item> itemList = itemRepository.findAll();
        Assertions.assertThat(itemList.size()).isEqualTo(2);
        Assertions.assertThat(itemList).contains(item1,item2);
    }

    @Test
    void updateItem() {
        //given
        Item item = new Item("itemA",10000,20);
        Item saveItem = itemRepository.save(item);
        Long itemId = saveItem.getId();
        //when
        Item updateParam = new Item("itemB",20000,30);
        itemRepository.update(itemId,updateParam);

        //then
        Item findItem = itemRepository.findById(itemId);
        Assertions.assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        Assertions.assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        Assertions.assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}
