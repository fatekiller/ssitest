from socket import socket
from threading import Thread
import threading
import time
import sys

IP = "127.0.0.1"

SAVE_PORT = 9991
FETCH_PORT = 9992
FILE_ROOT = "/root/disk_files/"
MAX_CONN_NUM = 1000

save_socket = socket()
fetch_socket = socket()


def accept_save_request():
    while True:
		print 'save'
		conn = save_socket.accept()[0]
		print 'save'
		t=Thread(target=save_file(conn=conn))
		t.setDaemon(True);
		t.start()


def save_file(conn):
    def do_save_file():
        file_name_byte = conn.recv(1024*64)
        file_name = ''.join(file_name_byte)
        conn.send('accepted')
        print file_name
        while True:
			file_obj = open(FILE_ROOT + file_name, "ab")
			data = conn.recv(1024*64)
			if data:
				file_obj.write(data)
			else:
				file_obj.close()
				break

    return do_save_file


def accept_fetch_request():
    while True:
		print 'fetch'
		conn = fetch_socket.accept()[0]
		print 'fetch'
		t=Thread(target=fetch_file(conn=conn))
		t.setDaemon(True)
		t.start()


def fetch_file(conn):
    def do_fetch_file():
        file_name_byte = conn.recv(1024*64)
        file_name = ''.join(file_name_byte)
        conn.send('accepted_fetch')
        print file_name
        file_obj = open(FILE_ROOT + file_name, "rb")
        conn.send(file_obj.read())

    return do_fetch_file
	
	
def block_to_exit():
	while True:
		a=raw_input()
		if a=="exit":
			sys.exit()
	


if __name__ == "__main__":
	save_socket.bind((IP, SAVE_PORT))
	fetch_socket.bind((IP, FETCH_PORT))
	save_socket.listen(MAX_CONN_NUM)
	fetch_socket.listen(MAX_CONN_NUM)
	t1=Thread(target=accept_save_request)
	t1.setDaemon(True)
	t1.start()
	t2=Thread(target=accept_fetch_request)
	t2.setDaemon(True)
	t2.start()
	ts=Thread(target=block_to_exit)
	ts.start()
		
