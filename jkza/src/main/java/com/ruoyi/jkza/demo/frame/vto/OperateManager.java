package com.ruoyi.jkza.demo.frame.vto;

import com.ruoyi.jkza.common.Base64;
import com.ruoyi.jkza.common.Res;
import com.ruoyi.jkza.demo.module.LoginModule;
import com.ruoyi.jkza.lib.NetSDKLib;
import com.ruoyi.jkza.lib.ToolKits;
import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.ruoyi.jkza.lib.NetSDKLib.EM_NET_RECORD_TYPE.NET_RECORD_ACCESSCTLCARD;
import static com.ruoyi.jkza.lib.NetSDKLib.NET_DEVSTATE_DEV_RECORDSET_EX;

public class OperateManager extends JDialog {

    private NetSDKLib.LLong lFindHandle;

    private JPanel contentPane;
    private OperateInfo info;
    private JTextField textField;
    private JTable table;
    private Object[][] data;
    private String[] tableTitle = {
            Res.string().getVTOOperateManagerRecNo(),
            Res.string().getVTOOperateManagerRoomNo(),
            Res.string().getVTOOperateManagerCardNo(),
            Res.string().getVTOOperateManagerFingerPrintData()
    };

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    OperateManager dialogManager = new OperateManager();
                    dialogManager.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialogManager.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public OperateManager() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Res.string().getVTOOperateManagerTitle());
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setBounds(100, 100, 547, 414);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, Res.string().getCardInfo(), TitledBorder.LEFT,
                TitledBorder.TOP, null, null));
        panel.setBounds(0, 10, 328, 356);
        contentPane.add(panel);
        panel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);
        data = new Object[0][5];
        table = tableInit(data, tableTitle);
        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel(Res.string().getVTORealLoadCardNo());
        lblNewLabel.setBounds(338, 24, 95, 20);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(338, 54, 136, 21);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnSearch = new JButton(Res.string().getSearch());
        btnSearch.setBounds(338, 85, 136, 23);
        contentPane.add(btnSearch);
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                search();
            }
        });

        JButton btnAdd = new JButton(Res.string().getAdd());
        btnAdd.setBounds(338, 138, 136, 23);
        contentPane.add(btnAdd);

        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (info == null) {
                    info = new OperateInfo();
                    info.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            super.windowClosed(e);
                            search();
                        }
                    });
                }
                info.setVisible(true);
                //?????????????????????
                info.setCardNoTextFieldEditEnable(true);
                //????????????????????????
                info.setRoomNoTextFieldEditEnable(true);
                info.setFocusable(true);
                //?????????
                info.receiveData(0, "", "", "");
                info.clearImage();
            }
        });
        JButton btnModify = new JButton(Res.string().getModify());
        btnModify.setBounds(338, 193, 136, 23);
        contentPane.add(btnModify);
        btnModify.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //modify();
                if (table.getSelectedRowCount() != 1) {
                    JOptionPane.showMessageDialog(null, Res.string().getSelectCard(), Res.string().getErrorMessage(),
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (table.getSelectedRowCount() == 1) {
                    if (info == null) {
                        info = new OperateInfo();
                        info.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                super.windowClosed(e);
                                search();
                            }
                        });
                    }
                    info.setVisible(true);
                    //????????????????????????
                    info.setCardNoTextFieldEditEnable(false);
                    //???????????????????????????
                    info.setRoomNoTextFieldEditEnable(false);
                    info.setFocusable(true);
                    //???????????????
                    /**
                     * ??????info?????????
                     */
                    info.receiveData(1, ((String) table.getModel().getValueAt(table.getSelectedRow(), 1)).trim(),
                            ((String) table.getModel().getValueAt(table.getSelectedRow(), 2)).trim(),
                            ((String) table.getModel().getValueAt(table.getSelectedRow(), 3)).trim());
                    //????????????????????????
                    info.clearImage();
                    info.setVisible(true);
                    info.setFocusable(true);
                }
            }
        });

        JButton btnDelete = new JButton(Res.string().getDelete());
        btnDelete.setBounds(338, 245, 136, 23);
        contentPane.add(btnDelete);
        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                delete();
                //?????????????????????
                search();
            }
        });

        JButton btnClear = new JButton(Res.string().getClear());
        btnClear.setBounds(338, 303, 136, 23);
        contentPane.add(btnClear);
        btnClear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                clear();
                //?????????????????????
                search();
            }
        });
    }

    /**
     * ???????????????
     *
     * @param data       ????????????
     * @param columnName ????????????
     * @return
     */
    public JTable tableInit(Object[][] data, String[] columnName) {
        JTable table;
        DefaultTableModel model;
        model = new DefaultTableModel(data, columnName);
        table = new JTable(model) {
            @Override // ????????????
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model = (DefaultTableModel) table.getModel();

        /*DefaultTableCellHeaderRenderer titleRender = new DefaultTableCellHeaderRenderer();
        titleRender.setHorizontalAlignment(JLabel.CENTER);
        table.getTableHeader().setDefaultRenderer(titleRender);*/

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // ??????????????????

        // ??????????????????
        DefaultTableCellRenderer dCellRenderer = new DefaultTableCellRenderer();
        dCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, dCellRenderer);
        return table;
    }

    /**
     * ???????????????
     */
    public void search() {
        if (lFindHandle != null && lFindHandle.longValue() != 0) {
            LoginModule.netsdk.CLIENT_FindRecordClose(lFindHandle);
            lFindHandle = null;
        }
        //??????table??????
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        //??????????????????
        NetSDKLib.NET_IN_FIND_RECORD_PARAM inParam = new NetSDKLib.NET_IN_FIND_RECORD_PARAM();
        NetSDKLib.NET_OUT_FIND_RECORD_PARAM outParam = new NetSDKLib.NET_OUT_FIND_RECORD_PARAM();
        //?????????
        inParam.emType = NET_RECORD_ACCESSCTLCARD;
        NetSDKLib.FIND_RECORD_ACCESSCTLCARD_CONDITION condition = new NetSDKLib.FIND_RECORD_ACCESSCTLCARD_CONDITION();
        if (textField.getText() != null && !textField.getText().equals("")) {
            //????????????,???????????????
            condition.abCardNo = 1;
            String cardNo = textField.getText();
            System.arraycopy(cardNo.getBytes(), 0, condition.szCardNo, 0, cardNo.getBytes().length);
            inParam.pQueryCondition = new Memory(condition.size());
            ToolKits.SetStructDataToPointer(condition, inParam.pQueryCondition, 0);
        }

        boolean bRet = LoginModule.netsdk.CLIENT_FindRecord(LoginModule.m_hLoginHandle, inParam, outParam, 10000);
        if (!bRet) {
            JOptionPane.showMessageDialog(null, ToolKits.getErrorCodeShow(), Res.string().getErrorMessage(),
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        lFindHandle = outParam.lFindeHandle;
        //Query??????????????????
        queryData();
        //????????????
        boolean success = LoginModule.netsdk.CLIENT_FindRecordClose(lFindHandle);
        if (!success) {
            JOptionPane.showMessageDialog(null, ToolKits.getErrorCodeShow(), Res.string().getErrorMessage(),
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * ???????????????????????????
     */
    public void queryData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        while (true) {
            int max = 20;
            //query the next batch of data ?????????????????????
            NetSDKLib.NET_IN_FIND_NEXT_RECORD_PARAM inParam = new NetSDKLib.NET_IN_FIND_NEXT_RECORD_PARAM();
            NetSDKLib.NET_OUT_FIND_NEXT_RECORD_PARAM outParam = new NetSDKLib.NET_OUT_FIND_NEXT_RECORD_PARAM();

            NetSDKLib.NET_RECORDSET_ACCESS_CTL_CARD[] cards = new NetSDKLib.NET_RECORDSET_ACCESS_CTL_CARD[max];
            for (int i = 0; i < max; i++) {
                cards[i] = new NetSDKLib.NET_RECORDSET_ACCESS_CTL_CARD();
            }

            outParam.pRecordList = new Memory(cards[0].size() * max);
            outParam.nMaxRecordNum = max;
            inParam.lFindeHandle = lFindHandle;
            inParam.nFileCount = max;
            ToolKits.SetStructArrToPointerData(cards, outParam.pRecordList);
            boolean result = LoginModule.netsdk.CLIENT_FindNextRecord(inParam, outParam, 10000);
            //????????????
            ToolKits.GetPointerDataToStructArr(outParam.pRecordList, cards);
            NetSDKLib.NET_RECORDSET_ACCESS_CTL_CARD card;
            for (int i = 0; i < outParam.nRetRecordNum; i++) {
                //??????????????????
                card = cards[i];
                //???????????????,?????????????????????
                String fingerPrint = "";
                card.bEnableExtended = 1;
                card.stuFingerPrintInfoEx.nPacketLen = 2048;
                card.stuFingerPrintInfoEx.pPacketData = new Memory(2048);
                //??????????????????
                fingerPrint = getFingerPrint(card);

                //??????table????????????
                model.addRow(new Object[]{card.nRecNo, new String(card.szUserID).trim(), new String(card.szCardNo).trim(), fingerPrint.trim()});
            }
            //???????????????????????????????????????,???????????????
            if (outParam.nRetRecordNum != outParam.nMaxRecordNum || (outParam.nRetRecordNum == 0 && outParam.nMaxRecordNum == 0)) {
                break;
            }
        }

    }

    /**
     * ????????????????????????
     *
     * @param card
     * @return
     */
    public String getFingerPrint(NetSDKLib.NET_RECORDSET_ACCESS_CTL_CARD card) {
        NetSDKLib.NET_CTRL_RECORDSET_PARAM inp = new NetSDKLib.NET_CTRL_RECORDSET_PARAM();
        inp.emType = NET_RECORD_ACCESSCTLCARD;
        inp.pBuf = new Memory(card.size());
        ToolKits.SetStructDataToPointer(card, inp.pBuf, 0);
        inp.nBufLen = card.size();
        Pointer pointer = new Memory(inp.size());
        ToolKits.SetStructDataToPointer(inp, pointer, 0);
        boolean re = LoginModule.netsdk.CLIENT_QueryDevState(LoginModule.m_hLoginHandle, NET_DEVSTATE_DEV_RECORDSET_EX, pointer, inp.size(), new IntByReference(0), 5000);
        if (re) {
            //??????????????????
            ToolKits.GetPointerDataToStruct(pointer, 0, inp);
            //?????????????????????
            ToolKits.GetPointerData(inp.pBuf, card);
            byte[] fpData = new byte[card.stuFingerPrintInfoEx.nRealPacketLen];
            card.stuFingerPrintInfoEx.pPacketData.read(0, fpData, 0, fpData.length);
            //??????base64??????
            return Base64.getEncoder().encodeToString(fpData);

        } else {
            /*JOptionPane.showMessageDialog(null, ToolKits.getErrorCodeShow(), Res.string().getErrorMessage(),
                    JOptionPane.ERROR_MESSAGE);*/
            System.out.println("Get Finger print data error.there is no data");
        }
        return "";
    }

    /**
     * ?????????????????????
     */
    public void delete() {
        if (table.getSelectedRowCount() != 1) {
            JOptionPane.showMessageDialog(null, "please select a card", Res.string().getErrorMessage(),
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        int nRecNo = (Integer) table.getModel().getValueAt(table.getSelectedRow(), 0);
        //?????????
        if (!deleteCard(nRecNo)) {
            return;
        }
        //????????????
        if (!deleteFaceInfo((String) table.getModel().getValueAt(table.getSelectedRow(), 1))) {
            return;
        }
    }

    /**
     * ???????????????
     *
     * @param recNo
     * @return
     */
    public boolean deleteCard(int recNo) {

        NetSDKLib.NET_CTRL_RECORDSET_PARAM remove = new NetSDKLib.NET_CTRL_RECORDSET_PARAM();
        remove.emType = NET_RECORD_ACCESSCTLCARD;
        remove.pBuf = new IntByReference(recNo).getPointer();

        remove.write();
        boolean result = LoginModule.netsdk.CLIENT_ControlDevice(LoginModule.m_hLoginHandle,
                NetSDKLib.CtrlType.CTRLTYPE_CTRL_RECORDSET_REMOVE, remove.getPointer(), 5000);
        remove.read();

        if (!result) {
            JOptionPane.showMessageDialog(null, ToolKits.getErrorCodeShow(), Res.string().getErrorMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    /**
     * ????????????(????????????)
     *
     * @param userId ??????ID
     */
    public boolean deleteFaceInfo(String userId) {
        int emType = NetSDKLib.EM_FACEINFO_OPREATE_TYPE.EM_FACEINFO_OPREATE_REMOVE;

        /**
         * ??????
         */
        NetSDKLib.NET_IN_REMOVE_FACE_INFO inRemove = new NetSDKLib.NET_IN_REMOVE_FACE_INFO();

        // ??????ID
        System.arraycopy(userId.getBytes(), 0, inRemove.szUserID, 0, userId.getBytes().length);

        /**
         *  ??????
         */
        NetSDKLib.NET_OUT_REMOVE_FACE_INFO outRemove = new NetSDKLib.NET_OUT_REMOVE_FACE_INFO();

        inRemove.write();
        outRemove.write();
        boolean bRet = LoginModule.netsdk.CLIENT_FaceInfoOpreate(LoginModule.m_hLoginHandle, emType, inRemove.getPointer(), outRemove.getPointer(), 5000);
        inRemove.read();
        outRemove.read();
        if (!bRet) {
            JOptionPane.showMessageDialog(null, Res.string().getRemoveCardFaceFailed() + "," + ToolKits.getErrorCodeShow(), Res.string().getErrorMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
        return bRet;
    }

    /**
     * ????????????
     */
    public void clear() {
        //??????????????????
        if (!clearFace()) {
            return;
        }
        //???????????????
        if (clearCard()) {
            return;
        }
        //????????????,????????????
        search();
    }

    /**
     * ????????????(????????????)
     */
    private boolean clearFace() {
        int emType = NetSDKLib.EM_FACEINFO_OPREATE_TYPE.EM_FACEINFO_OPREATE_CLEAR;

        /**
         *  ??????
         */
        NetSDKLib.NET_IN_CLEAR_FACE_INFO inClear = new NetSDKLib.NET_IN_CLEAR_FACE_INFO();

        /**
         *  ??????
         */
        NetSDKLib.NET_OUT_REMOVE_FACE_INFO outClear = new NetSDKLib.NET_OUT_REMOVE_FACE_INFO();

        inClear.write();
        outClear.write();
        boolean bRet = LoginModule.netsdk.CLIENT_FaceInfoOpreate(LoginModule.m_hLoginHandle, emType, inClear.getPointer(), outClear.getPointer(), 5000);
        inClear.read();
        outClear.read();
        if (!bRet) {
            JOptionPane.showMessageDialog(null, ToolKits.getErrorCodeShow(), Res.string().getErrorMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
        return bRet;
    }

    /**
     * ???????????????
     *
     * @return
     */
    private boolean clearCard() {
        NetSDKLib.NET_CTRL_RECORDSET_PARAM clear = new NetSDKLib.NET_CTRL_RECORDSET_PARAM();
        clear.emType = NetSDKLib.EM_NET_RECORD_TYPE.NET_RECORD_ACCESSCTLCARD;    // ?????????????????????

        clear.write();
        boolean result = LoginModule.netsdk.CLIENT_ControlDevice(LoginModule.m_hLoginHandle,
                NetSDKLib.CtrlType.CTRLTYPE_CTRL_RECORDSET_CLEAR, clear.getPointer(), 5000);
        clear.read();
        if (!result) {
            JOptionPane.showMessageDialog(null, ToolKits.getErrorCodeShow(), Res.string().getErrorMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

}
