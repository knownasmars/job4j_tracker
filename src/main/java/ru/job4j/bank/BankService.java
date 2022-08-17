package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает работу банковской программы в упрощенном виде.
 * Программа может добавлять в систему пользователя и его счета,
 * осуществлять поиск пользователя по паспортным данным и реквизитам,
 * а также осуществлять денежные переводы.
 * @author MARS SULEIMANOV
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение пользователя и его счетов осуществляется в коллекции
     * типа HashMap. В качестве пары ключ-значение в карату передается
     * польователь и список его счетов, счетов может быть несколько.
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает на вход пользователя и добавляет в систему.
     * @param user пользователь, который добавляется в систему.
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод принимает а вход паспортные данные и номер счета.
     * Проверяет существует ли пользователь с таким паспортом и
     * есть ли у него счет, переданный в параметрах. Если пользователь
     * существует, а счет отсутствует, то счет добавляется.
     * @param passport паспортные данные.
     * @param account счет пользователя.k
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user == null) {
            throw new IllegalArgumentException("Specified user is absent.");
        }
        List<Account> list = users.get(user);
        if (!list.contains(account)) {
            list.add(account);
        }
    }

    /**
     * Метод осуществляет поиск пользователя по паспортным данным.
     * Если такой пользователь существует, метод возвращает пользователя.
     * @param passport паспортные данные пользователя.
     * @return возвращает объект User - пользователя.
     * Если пользователь не существует, метод возвращает null.
     */
    public User findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод осуществляет поиск пользователя по паспортным данным
     * и проверяет существует ли счет с указанными в парарметрах реквизитами.
     * Если пользователь существует и у него имеется указанный счет, то
     * метод возвращает счет, в любом другом случае возвращает null.
     * @param passport паспортные данные.
     * @param requisite номер счета по которому осуществляется поиск.
     * @return возвращает счет пользователя, объект типа Account
     */
    public Account findByRequisite(String passport, String requisite) {
        User finding = findByPassport(passport);
        if (finding != null) {
            return users.get(finding)
                    .stream()
                    .filter(a -> a.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Метод осуществляет перевод денежных средств пользователя между счетами.
     * @param srcPassport паспортные данные отправителя денежных средств.
     * @param srcRequisite реквизиты счета, с которого будут списываться денежные средства.
     * @param destPassport паспортные данные получателя денежных средств.
     * @param destRequisite реквизиты счета, на который будут зачисляться денежные средства.
     * @param amount сумма перевода денежных средств.
     * @return возвращает true в случае, если получатель, отправитель и указанные в параметрах
     * счета существуют и перевод осуществлен успешно, в противном случае false.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null && srcAccount.getBalance() >= amount && destAccount != null) {
            destAccount.setBalance(destAccount.getBalance() + amount);
            srcAccount.setBalance(srcAccount.getBalance() - amount);
                return true;
        }
        return false;
    }

    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}